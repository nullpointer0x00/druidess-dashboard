package com.beatnikstree.druidess;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HelperUtils {

    public static String encodeToUrlUtf8(String url){
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
