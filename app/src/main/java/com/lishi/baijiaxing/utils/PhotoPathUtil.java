package com.lishi.baijiaxing.utils;

/**
 * Created by Administrator on 2016/12/20.
 */

public class PhotoPathUtil {
    public final static String WIDTH_640 = "?x-oss-process=style/rise_480"; 
//    public final static String WIDTH_640 = "?x-oss-process=style/rise_640"; 
//    public final static String WIDTH_640 = "?x-oss-process=style/rise_640"; 
//    public final static String WIDTH_640 = "?x-oss-process=style/rise_640"; 
    
    private PhotoPathUtil() {

    }

    public static class PhotoPathUtilHolder {
        private static PhotoPathUtil mPhotoPathUtil = new PhotoPathUtil();
    }

    public static PhotoPathUtil getInstance() {
        return PhotoPathUtilHolder.mPhotoPathUtil;
    }
}
