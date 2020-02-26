package com.lsl.pachong.run;

import com.lsl.demo.first.sys.entity.ArtistEntity;
import com.lsl.pachong.utils.common.Usually;
import com.lsl.pachong.utils.resolver.ArtistResolver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 * @author lisiliang
 * @since 2020/2/25
 */
public class RunArtist {

    public static ArtistEntity run(String urlPath) throws IOException {
        Document document = Jsoup.connect(urlPath)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .get();
        ArtistEntity rs =  ArtistResolver.resolve(document);
        Usually.print(rs);
        return rs;
    }


}
