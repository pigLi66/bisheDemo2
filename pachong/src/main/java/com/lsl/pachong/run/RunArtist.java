package com.lsl.pachong.run;

import com.lsl.demo.model.sys.entity.ArtistEntity;
import com.lsl.pachong.utils.resolver.ArtistResolver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * @author lisiliang
 * @since 2020/2/25
 */
public class RunArtist {

    public static ArtistEntity run(String urlPath) {
        ArtistEntity rs = null;
        try {
            Document document = Jsoup.connect(urlPath)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .get();
            rs = ArtistResolver.resolve(document);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-2);
        }
        return rs;
    }


}
