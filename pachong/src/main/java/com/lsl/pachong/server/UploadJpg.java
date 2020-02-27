package com.lsl.pachong.server;

import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lisiliang
 * @since 2020/2/24
 */
@Data
public class UploadJpg {

    private static final int DEFAULT_PORT = 9825;
    private static final String DEFAULT_HOST = "lslllxq.top";
    private String host;
    private int port;
    private static final int BUFFER_SIZE = 1024;

    public UploadJpg() {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public UploadJpg(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String uploadByURL(String urlPath) {
        try {
            URL url = new URL(urlPath);
            URLConnection connection = url.openConnection();

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");

            connection.connect();
            return upload(connection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("上传文件出错", e);
        }
    }

    public String upload(InputStream inputStream) {
        String fileUrl = null;
        try (Socket clientSocket = new Socket(this.host, this.port)) {
            InputStream socketIn = clientSocket.getInputStream();
            OutputStream socketOut = clientSocket.getOutputStream();

            // 得到文件路径
            int len = socketIn.read();
            byte[] fileUrlByte = new byte[len];
            socketIn.read(fileUrlByte, 0, len);
            fileUrl = new String(fileUrlByte);

            byte[] buffer = new byte[BUFFER_SIZE];
            while ((len = inputStream.read(buffer)) > 0) {
                socketOut.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    public String upload(String filePath) {
        try {
            InputStream fileIn = new FileInputStream(filePath);
            String fileUrl = this.upload(fileIn);
            fileIn.close();
            return fileUrl;
        } catch (IOException e) {
            throw new RuntimeException("上传文件出错", e);
        }
    }

}
