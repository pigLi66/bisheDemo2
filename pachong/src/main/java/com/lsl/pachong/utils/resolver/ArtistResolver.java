package com.lsl.pachong.utils.resolver;

import com.lsl.demo.first.sys.entity.ArtistEntity;
import com.lsl.pachong.utils.common.Usually;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;

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
        rs.setBirthday(parseBirthday(document));
        String[] t = document.baseUri().split("/");
        rs.setDoubanId(t[t.length - 1]);
        return rs;
    }

    private static String parseRealName(Document document) {
        String rs;
        try {
            Element elementDiv = document.getElementById("headline");
            Elements elementsImg = elementDiv.getElementsByTag("img");
            Element imgElement = elementsImg.get(0);
            rs = imgElement.attr("alt");
            return rs.split(" ")[0];
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        }
    }

    private static String parseSex(Document document) {
        try {
            StringBuilder rs = new StringBuilder();
            Element elementDiv = document.getElementById("headline");
            Elements liElements = elementDiv.getElementsByTag("li");
            liElements.forEach(item -> {
                Element t = item.getElementsByTag("span").get(0);
                if ("性别".equals(t.text())) {
                    rs.append(item.text());
                }
            });
            String[] t = rs.toString().split(":");
            String result = t[t.length - 1];
            return result.trim();
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        }
    }

    private static String parseBirthday(Document document) {
        StringBuilder rs = new StringBuilder();
        try {
            Element elementDiv = document.getElementById("headline");
            Elements liElements = elementDiv.getElementsByTag("li");
            liElements.forEach(item -> {
                Element t = item.getElementsByTag("span").get(0);
                if ("出生日期".equals(t.text())) {
                    rs.append(item.text());
                } else if ("生卒日期".equals(t.text())) {
                    rs.append(t.text().split("至")[0]);
                }
            });
            if (rs.length() == 0) {
                return null;
            }
            String[] t = rs.toString().split(":");
            if (t.length < 2) {
                return null;
            }
            return t[1];
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        } catch (NumberFormatException e) {
            Usually.print(document.baseUri());
            System.out.println(rs);
            return null;
        }
    }

    private static String parseBirthPlace(Document document) {
        try {
            StringBuilder rs = new StringBuilder();
            Element elementDiv = document.getElementById("headline");
            Elements liElements = elementDiv.getElementsByTag("li");
            liElements.forEach(item -> {
                Element t = item.getElementsByTag("span").get(0);
                if ("出生地".equals(t.text())) {
                    rs.append(item.text());
                }
            });
            String[] t = rs.toString().split(":");
            return t[t.length - 1].trim();
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        }
    }

    private static String parsePosition(Document document) {
        try {
            StringBuilder rs = new StringBuilder();
            Element elementDiv = document.getElementById("headline");
            Elements liElements = elementDiv.getElementsByTag("li");
            liElements.forEach(item -> {
                Element t = item.getElementsByTag("span").get(0);
                if ("职业".equals(t.text())) {
                    rs.append(item.text());
                }
            });
            String[] t = rs.toString().split(":");
            return t[t.length - 1].trim();
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        }
    }

    private static String parseProfile(Document document) {
        try {
            String rs = null;
            Elements allHiddenElements = document.getElementsByClass("all hidden");
            if (null != allHiddenElements &&
                    !allHiddenElements.isEmpty()) {
                rs = allHiddenElements.get(0).text();
            }
            if (rs == null) {
                return null;
            } else {
                return rs.trim();
            }
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        }
    }

    private static String parsePictureUrl(Document document) {
        try {
            String rs;
            Element headLineElement = document.getElementById("headline");
            Elements imgElements = headLineElement.getElementsByTag("img");
            rs = imgElements.get(0).attr("src");
            return rs;
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        }
    }

}
