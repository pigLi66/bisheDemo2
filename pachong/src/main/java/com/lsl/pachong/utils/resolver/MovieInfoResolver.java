package com.lsl.pachong.utils.resolver;

import com.lsl.pachong.entity.Human;
import com.lsl.pachong.entity.MovieInfoEntity;
import com.lsl.pachong.utils.constant.DoubanConstants;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lisiliang
 * @since 2020/2/25
 */
public class MovieInfoResolver {

    public static MovieInfoEntity resolver(Document document) {
        MovieInfoEntity rsEntity = new MovieInfoEntity();
        rsEntity.setDirectors(parseDirectors(document));
        rsEntity.setPerformers(parsePerformers(document));
        rsEntity.setReleaseTime(parseReleaseTime(document));
        rsEntity.setSpan(parseSpan(document));
        rsEntity.setProfile(parseProfile(document));
        rsEntity.setLevel(Double.parseDouble(parseLevel(document)));
        return rsEntity;
    }

    private static List<Human> parseDirectors(Document document) throws RuntimeException {
        List<Human> rsList = new ArrayList<>();
        try {
            Elements humanElements = document.getElementsByClass("info");
            // 获取导演和演员的信息
            humanElements.forEach(element -> {
                Element typeSpan = element.select("span").last();
                if (DoubanConstants.directorProperty.equals(typeSpan.text())) {
                    Element humanSpan = element.selectFirst("span");
                    Element humanA = humanSpan.selectFirst("a");
                    Human human = new Human();
                    human.setUrl(humanA.attr("href"));
                    human.setName(humanA.text());
                    rsList.add(human);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("parse directors exception", e);
        }
        return rsList;
    }

    private static List<Human> parsePerformers(Document document) {
        List<Human> rsList = new ArrayList<>();
        try {
            Elements humanElements = document.getElementsByClass("info");
            // 获取导演和演员的信息
            humanElements.forEach(element -> {
                Element typeSpan = element.select("span").last();
                if (!DoubanConstants.directorProperty.equals(typeSpan.text())) {
                    Element humanSpan = element.selectFirst("span");
                    Element humanA = humanSpan.selectFirst("a");
                    Human human = new Human();
                    human.setUrl(humanA.attr("href"));
                    human.setName(humanA.text());
                    rsList.add(human);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("parse performers exception", e);
        }
        return rsList;
    }

    private static String parseReleaseTime(Document document) {
        String rs;
        try {
            Element element = document.getElementById("info");
            Elements rtElements = element.getElementsByAttributeValue("property", "v:initialReleaseDate");
            rs = rtElements.stream().map(Element::text).collect(Collectors.joining(","));
        } catch (Exception e) {
            throw new RuntimeException("parse release time exception", e);
        }
        return rs;
    }

    private static String parseSpan(Document document) {
        String rs;
        try {
            Elements spanElements = document.getElementsByAttributeValue("property", "v:runtime");
            rs = spanElements.stream().map(Element::text).collect(Collectors.joining(","));
        } catch (Exception e) {
            throw new RuntimeException("parse span exception", e);
        }
        return rs;
    }

    private static String parseProfile(Document document) {
        String rs;
        try {
            Elements profileElemetns = document.getElementsByAttributeValue("property", "v:summary");
            rs = profileElemetns.stream().map(Element::text).collect(Collectors.joining(","));
        } catch (Exception e) {
            throw new RuntimeException("parse profile exception", e);
        }
        return rs;
    }

    private static String parseLevel(Document document) {
        String rs;
        try {
            Elements profileElemetns = document.getElementsByAttributeValue("property", "v:average");
            rs = profileElemetns.stream().map(Element::text).collect(Collectors.joining(","));
        } catch (Exception e) {
            throw new RuntimeException("parse level exception", e);
        }
        return rs;
    }

}
