package com.report.common.constants;

import com.report.common.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    static String year = DateUtil.getYear();
    public static final String[] imgSuffix = {"jpg", "jpeg", "png", "gif"};

    //本机配置
    public static final String distImagePathPrefix = "/Users/zks/servers/wp/wp-content/uploads/"+year+"/";
    public static final String rootFolder = "/Users/zks/Downloads/AI智能平台";
    public static final String imagesRootFolder = "/Users/zks/Downloads/批量图片上传";
    public static final int defaultTermId = 27;
    public static final String imgSrcPrefix = "http://testwp.zxxk.com/wp/wp-content/uploads/"+year+"/";
    public static final String documentUrlPrefix = "http://testwp.zxxk.com/wp/download/AI智能上传/";

    //服务器配置
//    public static final String distImagePathPrefix = "/Users/zxxk/server/wordpress/wp-content/uploads/"+year+"/";
//    public static final String rootFolder = "/Volumes/DesignDisk/zxxk/AI智能上传";
//    public static final String imagesRootFolder = "/Volumes/DesignDisk/zxxk/批量图片上传";
//    public static final int defaultTermId = 19;//默认是psd
//    public static final String imgSrcPrefix = "http://design.zxxk.com/wp-content/uploads/"+year+"/";
//    public static final String documentUrlPrefix = "http://design.zxxk.com/download/AI智能上传/";

    public static final Map<String,String> mimeType = new HashMap<String,String>(){
        {
            put("jpg","image/jpeg");
            put("jpeg","image/jpeg");
            put("png","image/png");
            put("gif","image/gif");
        }
    };
}
