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

    public static final String DEFAULT_URL = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=";

    private final String urlPath;

    public RunSearchSubjects() {
        this(DEFAULT_URL);
    }

    public RunSearchSubjects(String urlPath) {
        this.urlPath = urlPath;
    }

    public List<MovieBean> getTwenty(final int start) {
        try {
            // 1、 创建url
            URL url = new URL(urlPath + start);

            // 2、 创建urlConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 3、 设置报文
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
            connection.setRequestProperty("Accept", "application/json, text/plain, */*");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
           // connection.setRequestProperty("Cookie","ll=\"118267\"; bid=Cwadd-2nmFg; __yadk_uid=TAfseAuPxbVIx8xjyGiybFFzHeBTRzEP; viewed=\"2942431\"; gr_user_id=d8f893ee-90e6-44c7-a8cd-2d0981d8845e; __gads=ID=b8ce191ee1119982:T=1582188907:S=ALNI_Mb6ALL_hiseIjSu8xdBIxQTpdqr7A; douban-fav-remind=1; __utmz=30149280.1582678463.15.6.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmz=223695111.1582678464.15.6.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1582771785%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DNITI1xl7xmLBPHSthINYkkBFPsRm3-T6gKDWd05w2Q6uOyLojY1np1Zh4mg2tUPF%26wd%3D%26eqid%3Db9e8184f0008ddc4000000065e55c1b3%22%5D; __utma=30149280.822124607.1582166068.1582709456.1582771786.22; __utmc=30149280; __utma=223695111.616674577.1582166071.1582709456.1582771786.22; __utmc=223695111; _pk_id.100001.4cf6=78d8b2dbd5162a09.1582166071.21.1582773291.1582709463.; dbcl2=\"211389962:BAmDn3HLC/0\"; ck=41pq");

            // 4、 打开连接
            connection.connect();

            if (connection.getResponseCode() != HttpStatus.OK.value()) {
                throw new PaChongException("状态码异常url=" + urlPath + start + " 状态码=" + connection.getResponseCode());
            }

            List<MovieBean> rs = MovieBeanResolver.resolve(connection.getInputStream());
            return rs;
        } catch (IOException e) {
            throw new RuntimeException("获取首页json失败", e);
        }
    }

}
