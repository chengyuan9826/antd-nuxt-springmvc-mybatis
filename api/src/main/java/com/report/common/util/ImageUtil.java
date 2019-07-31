package com.report.common.util;

import com.marcospassos.phpserializer.Serializer;
import com.marcospassos.phpserializer.SerializerBuilder;
import com.report.controller.user.UserController;
import com.report.exceptions.ImageErrorException;
import com.report.model.wp.ImageMeta;
import org.apache.log4j.Logger;
import com.report.common.constants.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class ImageUtil {

    private static Logger log = Logger.getLogger(UserController.class);

    private static String DEFAULT_PREVFIX = "thumb_";
    private static Boolean DEFAULT_FORCE = false;//建议该值为false

    static Serializer serializer = new SerializerBuilder()
            .registerBuiltinAdapters()
            .build();


    public static String buildImgMetaStr(ImageMeta meta) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("width", meta.getWidth());
        map.put("height", meta.getHeight());
        map.put("file", meta.getYearMonthName());

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("aperture", 0);
        map2.put("credit", 0);
        map2.put("camera", "");
        map2.put("copyright", "");
        map2.put("focal_length", 0);

        map.put("image_meta", map2);
        return serializer.serialize(map);
    }

    /**
     * 获取图片的完整元数据
     *
     * @param file
     * @return
     */
    public static ImageMeta getImageMeta(File file) {
        ImageMeta meta = new ImageMeta();
        try {
            Image img = ImageIO.read(file);
            // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
            int width = img.getWidth(null);
            int height = img.getHeight(null);
            String fileName = file.getName();
            String ext = FileUtil.getFileExt(file);
            String mimeType = Constants.mimeType.get(ext);
            meta.setFileName(fileName);
            meta.setWidth(width);
            meta.setHeight(height);
            meta.setMimeType(mimeType);

            meta.setYearMonthName(DateUtil.getYear() + "/" + DateUtil.getMonth() + "/" + fileName);
        } catch (IOException e) {
            log.error("图片读取错误");
            e.printStackTrace();
        }
        return meta;
    }

    /**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 根据图片路径生成缩略图 </p>
     *
     * @param imagePath  原图片路径
     * @param distWidth  缩略图宽
     * @param distHeight 缩略图高
     */
    public static void thumbnailImage(String imagePath, String distPath, int distWidth, int distHeight) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()) try {
            // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
            String types = Arrays.toString(ImageIO.getReaderFormatNames());
            String suffix = null;
            String imgOriginName = null;
            // 获取图片后缀
            if (imgFile.getName().indexOf(".") > -1) {
                suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                imgOriginName = FileUtil.getFileNameWithoutExt(imgFile);
            }// 类型和图片后缀全部小写，然后判断后缀是否合法
            if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                return;
            }
            Image img = ImageIO.read(imgFile);
            // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
            int width = img.getWidth(null);
            int height = img.getHeight(null);
            if (distHeight == 0) {
                //如果高度等于0，按自适应比例缩放
                distHeight = distWidth * height / width;
            }

            BufferedImage bi = new BufferedImage(distWidth, distHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            g.drawImage(img, 0, 0, distWidth, distHeight, Color.LIGHT_GRAY, null);
            g.dispose();
            // 将图片保存在原目录并加上前缀
            String month = DateUtil.getMonth();
            File dist = new File(distPath + month + "/" + imgOriginName + "-" + distWidth + "x" + distHeight + "." + suffix);
            int count = 1;
            while (dist.exists()) {
                dist = new File(distPath + month + "/" + imgOriginName + "-" + count + "-" + distWidth + "x" + distHeight + "." + suffix);
                count++;
            }
            ImageIO.write(bi, suffix, dist);

            dist.setReadable(true, false);
            dist.setWritable(true, false);
            log.debug("缩略图生成成功，尺寸：" + distWidth + "x" + distHeight);
        } catch (IOException e) {
            log.error("generate thumbnail image failed.", e);
        }
        else {
            log.warn("the image is not exist.");
        }
    }


    /**
     * 生成不同尺寸的缩略图
     *
     * @param imagePath
     * @throws IOException
     */
    public static void generateImgThumbs(String imagePath) throws Exception {
        int[] widthArrays = {1870, 1024, 800, 768, 400, 300, 150};
        File imgFile = new File(imagePath);
        if (!imgFile.exists()) {
            log.error("图片不存在：" + imagePath);
            return;
        }
        Image img = null;
        img = ImageIO.read(imgFile);
        if (img == null) {
            //图片已损坏或者不是图片类型的文件
            throw new ImageErrorException("图片已损坏：" + imgFile.getName());
        }
        // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
        int width = img.getWidth(null);
        //首先把原图片拷贝过去
        String month = DateUtil.getMonth();

        FileUtil.fileCopy(imagePath, Constants.distImagePathPrefix + month + "/" + imgFile.getName());
        for (int i = 0; i < widthArrays.length; i++) {
            if (width > widthArrays[i]) {
                ImageUtil.thumbnailImage(imagePath, Constants.distImagePathPrefix, widthArrays[i], widthArrays[i] == 150 ? 150 : 0);
            }

        }
    }

    public static void main(String[] args) {
        System.out.println((String) null);

//        try {
////            ImageUtil.generateImgThumbs("/Users/zks/servers/wp/wp-content/uploads/2019/IMG_6810.jpg");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}