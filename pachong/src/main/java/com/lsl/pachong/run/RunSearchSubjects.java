package com.lsl.pachong.run;

import com.lsl.pachong.entity.MovieBean;
import com.lsl.pachong.exceptions.PaChongException;
import com.lsl.pachong.utils.resolver.MovieBeanResolver;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author lisiliang
 * @since 2020/2/25
 */
@Data
public class RunSearchSubjects {

    public static final String DEFAULT_URL = "https://movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start=";

    private final String urlPath;

    public RunSearchSubjects() {
        this(DEFAULT_URL);
    }

    public RunSearchSubjects(String urlPath) {
        this.urlPath = urlPath;
    }

    public List<MovieBean> getTwenty(final int start) throws IOException {
        // 1、 创建url
        URL url = new URL(urlPath + start);

        // 2、 创建urlConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 3、 设置报文
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        connection.setRequestProperty("Accept", "application/json, text/plain, */*");
        connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");

        // 4、 打开连接
        connection.connect();

        if (connection.getResponseCode() != HttpStatus.OK.value()) {
            throw new PaChongException("状态码异常url=" + urlPath + start + " 状态码=" + connection.getResponseCode());
        }

        return MovieBeanResolver.resolve(connection.getInputStream());
    }

}
