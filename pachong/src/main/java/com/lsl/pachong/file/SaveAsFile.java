package com.lsl.pachong.file;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lisiliang
 * @since 2020/2/25
 */
public class SaveAsFile {

    private static final int DEFAULT_BUFFER_SIZE = 1024;
    public static int bufferSize = DEFAULT_BUFFER_SIZE;

    /**
     * 保存输入流的内容到指定路径下的文件
     * @param filePath
     * @param inputStream
     */
    public static void save(String filePath, InputStream inputStream) {
        try (OutputStream fileOut = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[bufferSize];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                fileOut.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字符串保存到指定的文件中
     * @param filePath
     * @param content
     */
    public static void save(String filePath, String content) {
        SaveAsFile.save(filePath, new ByteArrayInputStream(content.getBytes()));
    }

}
