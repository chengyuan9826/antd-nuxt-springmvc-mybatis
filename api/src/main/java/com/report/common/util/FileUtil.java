package com.report.common.util;

import com.report.common.constants.Constants;
import com.report.controller.user.UserController;
import com.report.model.wp.Post;
import com.sun.tools.internal.jxc.ap.Const;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FileUtil {

    private static Logger log = Logger.getLogger(FileUtil.class);

    /**
     * 获取文件名称，不带后缀名
     *
     * @param file
     * @return
     */
    public static String getFileNameWithoutExt(File file) {
        return file.getName().substring(0, file.getName().lastIndexOf("."));
    }

    /**
     * 获取文件后缀名
     *
     * @param file
     * @return
     */
    public static String getFileExt(File file) {
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }

    /**
     * 把一个文件拷贝到另一个地方
     *
     * @param srcFile
     * @param distFile
     */
    public static String fileCopy(String srcFile, String distFile) {
        File file1 = new File(srcFile);
        File file2 = new File(distFile);
        String fileFolderPath = Constants.distImagePathPrefix + DateUtil.getMonth() + "/";

        String imgOriginName = FileUtil.getFileNameWithoutExt(file1);
        String newFileName = null;
        String ext = FileUtil.getFileExt(file1);
        if (!file1.exists()) {
            log.error("拷贝文件失败，文件不存在：" + srcFile);
            return null;
        }
        int count = 1;
        //如果文件已存在，自动重命名，在最后加上-1或者-2一次类推
        while (file2.exists()) {
            newFileName = imgOriginName + "-" + count;
            file2 = new File(fileFolderPath + newFileName + "." + ext);
//            file2 = new File(distFile.replace(imgOriginName, newFileName));
            count++;
        }
        try {
            Files.copy(file1.toPath(), file2.toPath());
            file2.setReadable(true, false);
            file2.setWritable(true, false);
            log.debug("文件拷贝成功");
        } catch (IOException e) {
            log.error("文件拷贝失败");
            e.printStackTrace();
        }
        return newFileName;
    }


    public static class DSStoreFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return name.indexOf("DS_Store") == -1 && name.indexOf("已生成") == -1 && name.indexOf(".") != 0;
        }

    }

    public static boolean isImg(String ext) {
        return Arrays.asList(Constants.imgSuffix).contains(ext);
    }

    public static Object getFileList() {

        System.out.println(Calendar.getInstance().get(Calendar.YEAR));

//        for (int i = 0; i < lists.length; i++) {
//            File userRootFolder = lists[i];
//            File[] userRootFolderFiles = userRootFolder.listFiles(filter);
//            System.out.println("用户名：" + userRootFolder.getName());
//
//            for (int j = 0; j < userRootFolderFiles.length; j++) {
//                File userDocumentFolder = userRootFolderFiles[j];
//                File[] userDocumentFolderFiles = userDocumentFolder.listFiles(filter);
//                System.out.println("文章标题:" + userDocumentFolder.getName());
//
//                for (int k = 0; k < userDocumentFolderFiles.length; k++) {
//                    File userDocument = userDocumentFolderFiles[k];
//                    String ext = FileUtil.getFileExt(userDocument);
//                    String path = userDocument.getPath();
//                    boolean isImg = FileUtil.isImg(ext);
//                    System.out.println("文件:" + path);
//                    System.out.println("后缀名:" + ext);
//                    System.out.println("是否图片:" + isImg);
//                }
//            }
//        }
        return null;
    }

    /**
     * 返回2019/04/xxxx.jpg类似的名称
     *
     * @param file
     * @return
     */
    public static String getYearMonthName(File file) {
        String yearMonthName = DateUtil.getYear() + "/" + DateUtil.getMonth() + "/" + file.getName();
        return yearMonthName;
    }

    public static void main(String[] args) {
        getFileList();
    }
}
