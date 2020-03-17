package com.lsl.demo.model.sys.service.impl;

import com.lsl.demo.common.exceptions.BaseException;
import com.lsl.demo.model.sys.service.IJpgService;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author lisiliang
 * @since 2020/3/17
 */
@Service
public class JpgServiceImpl implements IJpgService {

    private static final String OUT_PATH = "F:\\实习_2019\\stu\\spring_stu_Internet\\bisheDemo2\\admin\\jpg\\";

    private static Integer count = 0;

    @Override
    public String saveJpg(InputStream inputStream) {
        String rs;
        File file;
        synchronized (JpgServiceImpl.class) {
            do {
                rs = OUT_PATH + count;
                file = new File(rs);
                count++;
            } while (file.exists());
            try {
                if (!file.createNewFile()) {
                    throw new BaseException("文件上传失败", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new BaseException("文件上传失败", HttpStatus.SC_INTERNAL_SERVER_ERROR);
            }
        }

        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }

        return rs;
    }

}
