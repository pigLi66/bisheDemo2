package com.lsl.pachong.utils.resolver;

import com.lsl.pachong.entity.Human;
import com.lsl.pachong.entity.MovieInfoEntity;
import com.lsl.pachong.exceptions.ResolverException;
import com.lsl.pachong.utils.common.Usually;
import com.lsl.pachong.utils.constant.DoubanConstants;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

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
        rsEntity.setLevel(parseLevel(document));
        rsEntity.setPicUrl(parsePictureUrl(document));
        rsEntity.setDoubanId(parseDoubanId(document));
        rsEntity.setClassId(parseClassId(document));
        return rsEntity;
    }

    private static String parseClassId(Document document) {
        try {
            Elements elements = document.getElementsByAttributeValue("property", "v:genre");
            return elements.stream().map(Element::text).collect(Collectors.joining(",")).trim();
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return "";
        }
    }

    private static String parseDoubanId(Document document) {
        try {
            String[] t = document.baseUri().split("/");
            return t[t.length - 1].trim();
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return "";
        }
    }

    private static List<Human> parseDirectors(Document document) throws RuntimeException {
        try {
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
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        }
    }

    private static List<Human> parsePerformers(Document document) {
        try {
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
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        }
    }

    private static String parseReleaseTime(Document document) {
        try {
            String rs;
            try {
                Element element = document.getElementById("info");
                Elements rtElements = element.getElementsByAttributeValue("property", "v:initialReleaseDate");
                rs = rtElements.stream().map(Element::text).collect(Collectors.joining(","));
            } catch (Exception e) {
                throw new RuntimeException("parse release time exception", e);
            }
            return rs.trim();
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return "";
        }
    }

    private static String parseSpan(Document document) {
        try {
            String rs;
            try {
                Elements spanElements = document.getElementsByAttributeValue("property", "v:runtime");
                rs = spanElements.stream().map(Element::text).collect(Collectors.joining(","));
            } catch (Exception e) {
                throw new RuntimeException("parse span exception", e);
            }
            return rs.trim();
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return "";
        }
    }

    private static String parseProfile(Document document) {
        try {
            String rs;
            try {
                Element element = document.getElementById("link-report");
                Elements elements = element.getElementsByClass("all hidden");
                if (CollectionUtils.isEmpty(elements)) {
                    Elements es = element.getElementsByTag("span");
                    if (es.size() > 0) {
                        rs = es.get(0).text();
                    } else {
                        rs = "";
                    }
                } else {
                    rs = elements.stream().map(Element::text).collect(Collectors.joining(","));
                }
            } catch (Exception e) {
                throw new RuntimeException("parse profile exception", e);
            }
            return rs.trim();
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return "";
        }
    }

    private static Double parseLevel(Document document) {
        try {
            String rs;
            try {
                Elements profileEasements = document.getElementsByAttributeValue("property", "v:average");
                rs = profileEasements.stream().map(Element::text).collect(Collectors.joining(","));
            } catch (Exception e) {
                throw new RuntimeException("parse level exception", e);
            }
            return Double.parseDouble(rs);
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return null;
        }
    }

    private static String parsePictureUrl(Document document) {
        try {
            String rs;
            try {
                Element element = document.getElementById("mainpic");
                Elements elements = element.getElementsByTag("img");
                if (elements.isEmpty()) {
                    return "";
                }
                rs = elements.get(0).attr("src");
            } catch (Exception e) {
                throw new ResolverException("解析图片异常", e);
            }
            return rs;
        } catch (NullPointerException e) {
            Usually.print(document.baseUri());
            e.printStackTrace();
            return "";
        }
    }

}
