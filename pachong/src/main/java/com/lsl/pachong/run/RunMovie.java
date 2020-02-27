package com.lsl.pachong.run;

import com.lsl.pachong.entity.MovieInfoEntity;
import com.lsl.pachong.exceptions.PaChongException;
import com.lsl.pachong.utils.common.Usually;
import com.lsl.pachong.utils.resolver.MovieInfoResolver;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;

/**
 * @author lisiliang
 * @since 2020/2/25
 */
@Data
public class RunMovie {

    public static MovieInfoEntity run(String urlPath) {
        MovieInfoEntity rs = null;
        try {
            Document document = Jsoup.connect(urlPath)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .get();
            if (Objects.isNull(document)) {
                throw new PaChongException("爬虫失误");
            }
            // 获取电影信息，包括 导演、演员、类型、片长等
            rs = MovieInfoResolver.resolver(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}
