package com.lsl.demo.common.server;

import java.io.InputStream;
import java.net.UnknownHostException;

/**
 * @author lisiliang
 * @since 2020/2/26
 */
public class UploadJpgToMyLinux {

    private UploadJpgToMyLinux() {

    }

    private static final UploadJpg SERVER;

    static {
        UploadJpg t;
        try {
            t = new UploadJpg();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            t = null;
        }
        SERVER = t;
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
