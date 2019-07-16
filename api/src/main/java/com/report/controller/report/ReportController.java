package com.report.controller.report;

import com.report.common.constants.Constants;
import com.report.common.util.DateUtil;
import com.report.common.util.FileUtil;
import com.report.common.util.ImageUtil;
import com.report.controller.user.UserController;
import com.report.dao.report.ReportMapper;
import com.report.exceptions.ImageErrorException;
import com.report.model.report.Report;
import com.report.model.user.User;
import com.report.model.wp.ImageMeta;
import com.report.model.wp.Post;
import com.report.model.wp.PostMeta;
import com.report.service.user.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ReportMapper reportMapper;

    Logger log = Logger.getLogger(ReportController.class);

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 查询报表
     *
     * @param request
     * @param params
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryReport.do")
    public Map<String, Object> queryReport(HttpServletRequest request, @RequestBody(required = false) Map<String, Object> params, HttpSession session) {
        //取参数
        if (params != null) {
            String fileType = (String) params.get("fileType");
            String likeUserName = (String) params.get("likeUserName");
            String notLikeUserName = (String) params.get("notLikeUserName");
            Integer minNum = (Integer) params.get("minNum");
            Integer maxNum = (Integer) params.get("maxNum");
        }
        //查询数据库
        List<Report> list = reportMapper.queryReport(params);
        //返回结果
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = null;
        int state = 0;
        result.put("state", state);
        result.put("msg", msg);
        result.put("list", list);
        return result;
    }

    /**
     * 批量发表文章，一个psd搭配一个图片
     *
     * @param request
     * @param folderName
     * @param session
     * @return
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/batchInsert.do")
    public Map<String, Object> batchInsert(HttpServletRequest request, String folderName, HttpSession session) {
        //返回的信息
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = null;
        int state = 0;
        if (folderName == null) {
            result.put("state", -1);
            result.put("msg", "folderName不能为空");
            return result;
        }
        log.info("开始生成文章：");
        //获取文件列表
        File folder = new File(Constants.rootFolder + "/" + folderName);
        FilenameFilter filter = new FileUtil.DSStoreFilter();
        if (!folder.exists()) {
            result.put("state", -1);
            result.put("msg", "文件夹不存在: " + Constants.rootFolder + "/" + folderName);
            return result;
        }
        File[] lists = folder.listFiles(filter);
        String documentUrl = null;
        String imgUrl = null;
        String imgFileName = null;
        String imgYearMonthName = null;
        String month = DateUtil.getMonth();
        Date time = new Date();
        //timeGmt时间要小一些，不然的话文章审核的时候没有发布按钮，只有计划按钮
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date timeGmt = c.getTime();
        int postId = 0;
        int attachmentId = 0;
        String imgMetaStr = null;
        ImageMeta imgMeta = null;
        int count = 0;
        int errorCount= 0;
        UUID uuid = null;
        //失败的列表
        List<String> errList = new ArrayList<String>();
        for (int i = 0; i < lists.length; i++) {
            File userRootFolder = lists[i];
            File[] userRootFolderFiles = userRootFolder.listFiles(filter);
            String username = userRootFolder.getName();
            log.debug("用户名：" + username);
            //根据用户名找到用户的ID
            User user = userService.queryUserByUsername(username);
            int userId = user.getId();

            for (int j = 0; j < userRootFolderFiles.length; j++) {
                File userDocumentFolder = userRootFolderFiles[j];
                File[] userDocumentFolderFiles = userDocumentFolder.listFiles(filter);
                String title = userDocumentFolder.getName();
                log.debug("文章标题:" + title);

                for (int k = 0; k < userDocumentFolderFiles.length; k++) {
                    File userDocument = userDocumentFolderFiles[k];
                    String filePath = userDocument.getPath();
                    imgFileName = userDocument.getName();
                    String ext = FileUtil.getFileExt(userDocument);
                    boolean isImg = FileUtil.isImg(ext);
                    //如果是图片，拷贝到upload文件夹
                    if (isImg) {
                        try {
                            //首先重命名
                            String parentPath = userDocument.getParent();
                            uuid = UUID.randomUUID();
                            //利用uuid重命名图片，避免重复
                            File newFile = new File(parentPath + "/" + uuid + "." + ext);
                            userDocument.renameTo(newFile);
                            newFile.setReadable(true, false);
                            newFile.setWritable(true, false);
                            userDocument = newFile;
                            try {
                                ImageUtil.generateImgThumbs(newFile.getPath());

                            } catch (ImageErrorException e) {
                                errorCount++;
                                log.error(e.getMessage());
                                errList.add(newFile.getAbsolutePath());
                                continue;
                            }
                            imgYearMonthName = FileUtil.getYearMonthName(userDocument);
                        } catch (Exception e) {
                            log.error("生成文件缩略图失败：" + filePath);
                            e.printStackTrace();
                        }
                        imgUrl = Constants.imgSrcPrefix + month + "/" + userDocument.getName();
                        imgMeta = ImageUtil.getImageMeta(userDocument);
                        imgMetaStr = ImageUtil.buildImgMetaStr(imgMeta);
                    } else {
                        documentUrl = Constants.documentUrlPrefix + folderName + "/" + username + "/" + title + "/" + imgFileName;
                        log.debug("文件下载路径：" + documentUrl);
                    }
                    log.debug("文件:" + userDocument.getPath());
                }
                //有的人只上传了一个图片，没有源文件，需要单独处理，这里把documentUrl设置为null
                if (userDocumentFolderFiles.length == 1) {
                    documentUrl = null;
                }
                //插入文章
                Post post = getPost(documentUrl, imgUrl, time, timeGmt, userId, title);
                reportMapper.insertPost(post);
                postId = post.getId();
                log.debug("文章内容添加成功：" + postId);

                //在post表中插入图片
                Post attachment = getPost(documentUrl, imgUrl, imgFileName, time, postId, imgMeta, userId);
                reportMapper.insertPost(attachment);
                attachmentId = attachment.getId();
                log.debug("文章缩略图插入成功：" + attachmentId);

                //在postmeta表中插入图片元数据:名称
                PostMeta meta1 = getPostMeta(imgYearMonthName, attachmentId, "_wp_attached_file");
                reportMapper.insertPostMeta(meta1);
                log.debug("图片名称插入成功");

                //postmeta插入多种尺寸的元数据：尺寸大小
                PostMeta meta2 = getPostMeta(imgMetaStr, attachmentId, "_wp_attachment_metadata");
                reportMapper.insertPostMeta(meta2);
                log.debug("图片尺寸元数据插入成功");

                //postmeta把图片和文章关联起来
                PostMeta meta3 = getPostMeta(String.valueOf(attachmentId), postId, "_thumbnail_id");
                reportMapper.insertPostMeta(meta3);
                log.debug("图片和文章id关联成功");

                //给文章关联分类
                Map<String, Object> termrelationships = getStringObjectMap(postId);
                reportMapper.insertTermRelations(termrelationships);
                log.debug("文章关联分类成功");
                log.debug("第" + ++count + "篇文章添加成功：" + userDocumentFolder.getPath());
            }
        }
        log.debug("所有文章插入成功，");
        result.put("state", 0);
        result.put("errorList",errList);
        result.put("msg", "批量插入成功，成功条数：" + count + "，失败条数：" + errorCount + "。具体失败信息请查看后端日志。");
        return result;
    }


    /**
     * 批量发表文章，一个目录下有上千张图片
     *
     * @param request
     * @param folderName
     * @param session
     * @return
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/batchInsertImages.do")
    public Map<String, Object> batchInsertImages(HttpServletRequest request, String folderName, String slug, HttpSession session) throws UnsupportedEncodingException {
        //返回的信息
        Map<String, Object> result = new HashMap<String, Object>();
        if (folderName == null) {
            result.put("state", -1);
            result.put("msg", "folderName不能为空");
            return result;
        }
        if (slug == null) {
            result.put("state", -1);
            result.put("msg", "请输入分类的slug");
            return result;
        }
        log.info("开始生成文章：");
        //获取文件列表
        File folder = new File(Constants.imagesRootFolder + "/" + folderName);
        FilenameFilter filter = new FileUtil.DSStoreFilter();
        if (!folder.exists()) {
            result.put("state", -1);
            result.put("msg", "文件夹不存在: " + Constants.imagesRootFolder + "/" + folderName);
            return result;
        }
        File[] lists = folder.listFiles(filter);
        String documentUrl = null;
        String imgUrl = null;
        String imgFileName = null;
        String imgYearMonthName = null;
        String month = DateUtil.getMonth();
        Date time = new Date();
        //timeGmt时间要小一些，不然的话文章审核的时候没有发布按钮，只有计划按钮
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date timeGmt = c.getTime();
        int postId = 0;
        int attachmentId = 0;
        String imgMetaStr = null;
        ImageMeta imgMeta = null;
        int count = 0;
        int errorCount = 0;
        UUID uuid = null;
        for (int i = 0; i < lists.length; i++) {
            File userRootFolder = lists[i];
            String username = userRootFolder.getName();
            log.debug("用户名：" + username);
            //根据用户名找到用户的ID
            User user = userService.queryUserByUsername(username);
            int userId = user.getId();

            File[] images = userRootFolder.listFiles(filter);
//            for (int j = 0; j < userRootFolderFiles.length; j++) {
//                File userDocumentFolder = userRootFolderFiles[j];
//                File[] userDocumentFolderFiles = userDocumentFolder.listFiles(filter);
//                String title = userDocumentFolder.getName();
//                log.debug("文章标题:" + title);

            for (int k = 0; k < images.length; k++) {
//                try {
                File userDocument = images[k];
                String filePath = userDocument.getPath();
                imgFileName = userDocument.getName();
                String title = FileUtil.getFileNameWithoutExt(userDocument);
                String ext = FileUtil.getFileExt(userDocument);
                boolean isImg = FileUtil.isImg(ext);
                //如果是图片，拷贝到upload文件夹
                if (isImg) {
                    try {
                        //首先重命名
                        String parentPath = userDocument.getParent();
                        uuid = UUID.randomUUID();
                        //利用uuid重命名图片，避免重复
                        File newFile = new File(parentPath + "/" + uuid + "." + ext);
                        userDocument.renameTo(newFile);
                        newFile.setReadable(true, false);
                        newFile.setWritable(true, false);
                        userDocument = newFile;
                        try {
                            ImageUtil.generateImgThumbs(newFile.getPath());
                        } catch (ImageErrorException e) {
                            errorCount++;
                            log.error(e.getMessage());
                            continue;
                        }
                        imgYearMonthName = FileUtil.getYearMonthName(userDocument);
                    } catch (Exception e) {
                        log.error("生成文件缩略图失败：" + filePath);
                        e.printStackTrace();
                    }
                    imgUrl = Constants.imgSrcPrefix + month + "/" + userDocument.getName();
                    imgMeta = ImageUtil.getImageMeta(userDocument);
                    imgMetaStr = ImageUtil.buildImgMetaStr(imgMeta);
                } else {
                    log.error("文件不是图片类型：" + imgFileName);
                }
                log.debug("文件:" + userDocument.getPath());

                //插入文章
                Post post = getPost(documentUrl, imgUrl, time, timeGmt, userId, title);
                reportMapper.insertPost(post);
                postId = post.getId();
                log.debug("文章内容添加成功：" + postId);

                //在post表中插入图片
                Post attachment = getPost(documentUrl, imgUrl, imgFileName, time, postId, imgMeta, userId);
                reportMapper.insertPost(attachment);
                attachmentId = attachment.getId();
                log.debug("文章缩略图插入成功：" + attachmentId);

                //在postmeta表中插入图片元数据:名称
                PostMeta meta1 = getPostMeta(imgYearMonthName, attachmentId, "_wp_attached_file");
                reportMapper.insertPostMeta(meta1);
                log.debug("图片名称插入成功");

                //postmeta插入多种尺寸的元数据：尺寸大小
                PostMeta meta2 = getPostMeta(imgMetaStr, attachmentId, "_wp_attachment_metadata");
                reportMapper.insertPostMeta(meta2);
                log.debug("图片尺寸元数据插入成功");

                //postmeta把图片和文章关联起来
                PostMeta meta3 = getPostMeta(String.valueOf(attachmentId), postId, "_thumbnail_id");
                reportMapper.insertPostMeta(meta3);
                log.debug("图片和文章id关联成功");

                //给文章关联分类
                int termTaxonomyId = queryTermIdBySlug(slug);
                if (termTaxonomyId == 0) {
                    result.put("state", -1);
                    result.put("msg", "传入的slug有误，请检查");
                    return result;
                }
                Map<String, Object> termrelationships = getStringObjectMap(postId, termTaxonomyId);
                reportMapper.insertTermRelations(termrelationships);
                log.debug("文章关联分类成功");
                log.debug("第" + ++count + "篇文章添加成功：" + userDocument.getPath());
            }
        }
        log.debug("所有文章插入成功，");
        result.put("state", 0);
        result.put("msg", "批量插入成功，成功条数：" + count + "，失败条数：" + errorCount + "。具体失败信息请查看后端日志。");
        return result;
    }

    /**
     * 批量更新一个用户的文章为已审核状态
     *
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchPublish.do")
    public Map<String, Object> batchPubish(HttpServletRequest request, String username, HttpSession session) {
        //取参数
        //返回结果
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = "更新成功";
        int state = 0;
        int rows = 0;
        //更新数据库
        try {
            rows = reportMapper.batchPublish(username);
        } catch (Exception e) {
            state = -1;
            msg = e.getMessage();
        }
        result.put("state", state);
        result.put("msg", msg);
        result.put("rows", rows);
        return result;
    }

    /**
     * 给文章关联固定分类
     *
     * @param postId
     * @return
     */
    private Map<String, Object> getStringObjectMap(int postId) {
        Map<String, Object> termrelationships = new HashMap<String, Object>();
        termrelationships.put("post_id", postId);
        termrelationships.put("term_id", Constants.defaultTermId);
        return termrelationships;
    }

    /**
     * 根据slug查询termTaxonomyId
     *
     * @param slug
     * @return
     */
    private int queryTermIdBySlug(String slug) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("slug", URLEncoder.encode(slug,"UTF-8").toLowerCase());
        List<Map<String, Object>> result = reportMapper.queryTermInfo(map);
        int termTaxonomyId = 0;
        if (result.size() > 0) {
            map = result.get(0);
            BigInteger id = (BigInteger) map.get("term_taxonomy_id");
            termTaxonomyId = id.intValue();
        }
        return termTaxonomyId;
    }

    /**
     * 给文章关联指定分类
     *
     * @param postId
     * @return
     */
    private Map<String, Object> getStringObjectMap(int postId, int termTaxonomyId) {
        Map<String, Object> termrelationships = new HashMap<String, Object>();
        termrelationships.put("post_id", postId);
        termrelationships.put("term_id", termTaxonomyId);
        return termrelationships;
    }

    private PostMeta getPostMeta(String imgYearMonthName, int attachmentId, String wp_attached_file) {
        PostMeta meta1 = new PostMeta();
        meta1.setPost_id(attachmentId);
        meta1.setMeta_key(wp_attached_file);
        meta1.setMeta_value(imgYearMonthName);
        return meta1;
    }

    private Post getPost(String documentUrl, String imgUrl, String imgFileName, Date time, int postId, ImageMeta imgMeta, int userId) {
        Post attachment = new Post();
        attachment.setPost_author(userId);
        attachment.setPost_date(time);
        attachment.setPost_date_gmt(time);
        attachment.setPost_content(documentUrl, imgUrl);
        attachment.setPost_title(imgFileName);
        attachment.setPost_excerpt("");
        attachment.setPost_status("inherit");
        attachment.setPost_type("attachment");
        attachment.setPost_name(imgFileName);
        attachment.setPost_modified(time);
        attachment.setPost_modified_gmt(time);
        attachment.setPost_parent(postId);
        attachment.setPinged("");
        attachment.setTo_ping("");
        attachment.setPost_mime_type(imgMeta.getMimeType());
        attachment.setPost_content_filtered("");
        return attachment;
    }

    private Post getPost(String documentUrl, String imgUrl, Date time, Date timeGmt, int userId, String title) {
        Post post = new Post();
        post.setPost_author(userId);
        post.setPost_date(time);
        post.setPost_date_gmt(timeGmt);
        post.setPost_content(documentUrl, imgUrl);
        post.setPost_title(title);
        post.setPost_excerpt("");
        post.setPost_status("pending");
        post.setPost_name(title);
        post.setPost_modified(time);
        post.setPost_modified_gmt(timeGmt);
        post.setPost_parent(0);
        post.setPinged("");
        post.setTo_ping("");
        post.setPost_type("post");
        post.setPost_content_filtered("");
        post.setPost_mime_type("");
        return post;
    }

}
