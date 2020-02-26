package com.lsl.pachong.utils.resolver;

import cn.hutool.core.collection.CollectionUtil;
import com.lsl.demo.first.sys.entity.ArtistEntity;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author lisiliang
 * @since 2020/2/26
 */
public class ArtistResolver {

    public static ArtistEntity resolve(Document document) {
        ArtistEntity rs = new ArtistEntity();
        rs.setRealName(parseRealName(document));
        rs.setSex(parseSex(document));
        rs.setBirthPlace(parseBirthPlace(document));
        rs.setPosition(parsePosition(document));
        rs.setProfile(parseProfile(document));
        rs.setPictureUrl(parsePictureUrl(document));
        return rs;
    }

    private static String parseRealName(Document document) {
        String rs;
        Element elementDiv = document.getElementById("headline");
        Elements elementsImg = elementDiv.getElementsByTag("img");
        Element imgElement = elementsImg.get(0);
        rs = imgElement.attr("alt");
        return rs;
    }

    private static String parseSex(Document document) {
        StringBuilder rs = new StringBuilder();
        Element elementDiv = document.getElementById("headline");
        Elements liElements = elementDiv.getElementsByTag("li");
        liElements.forEach(item->{
            Element t = item.getElementsByTag("span").get(0);
            if ("性别".equals(t.text())) {
                rs.append(item.text());
            }
        });
        return rs.toString();
    }

    private static String parseBirthday(Document document) {
        StringBuilder rs = new StringBuilder();
        Element elementDiv = document.getElementById("headline");
        Elements liElements = elementDiv.getElementsByTag("li");
        liElements.forEach(item->{
            Element t = item.getElementsByTag("span").get(0);
            if ("出生日期".equals(t.text())) {
                rs.append(item.text());
            }
        });
        return rs.toString();
    }

    private static String parseBirthPlace(Document document) {
        StringBuilder rs = new StringBuilder();
        Element elementDiv = document.getElementById("headline");
        Elements liElements = elementDiv.getElementsByTag("li");
        liElements.forEach(item->{
            Element t = item.getElementsByTag("span").get(0);
            if ("出生地".equals(t.text())) {
                rs.append(item.text());
            }
        });
        return rs.toString();
    }

    private static String parsePosition(Document document) {
        StringBuilder rs = new StringBuilder();
        Element elementDiv = document.getElementById("headline");
        Elements liElements = elementDiv.getElementsByTag("li");
        liElements.forEach(item->{
            Element t = item.getElementsByTag("span").get(0);
            if ("职业".equals(t.text())) {
                rs.append(item.text());
            }
        });
        return rs.toString();
    }

    private static String parseProfile(Document document) {
        String rs = null;
        Elements allHiddenElements = document.getElementsByClass("all hidden");
        if (null != allHiddenElements &&
                !allHiddenElements.isEmpty()) {
            rs = allHiddenElements.get(0).text();
        }
        return rs;
    }

    private static String parsePictureUrl(Document document) {
        String rs;
        Element headLineElement = document.getElementById("headline");
        Elements imgElements =  headLineElement.getElementsByTag("img");
        rs = imgElements.get(0).attr("src");
        return rs;
    }
}
