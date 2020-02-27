package com.lsl.pachong.server;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lisiliang
 * @since 2020/2/26
 */
public class UploadJpgToMyLinux {

    private UploadJpgToMyLinux() {

    }

    private static final UploadJpg SERVER;

    static {
        SERVER = new UploadJpg();
    }

    public static String upload(String filePath) {
        return SERVER.upload(filePath);
    }

    public static String upload(InputStream inputStream) {
        return SERVER.upload(inputStream);
    }

    public static String uploadByURL(String urlPath) {
        return SERVER.uploadByURL(urlPath);
    }

}
