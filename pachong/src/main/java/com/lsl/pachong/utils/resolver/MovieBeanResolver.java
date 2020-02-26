package com.lsl.pachong.utils.resolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsl.pachong.entity.MovieBean;
import com.lsl.pachong.exceptions.ResolverException;
import com.lsl.pachong.utils.common.Usually;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author lisiliang
 * @since 2020/2/25
 */
public class MovieBeanResolver {

    public static List<MovieBean> resolve(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray data = jsonObject.getJSONArray("data");
        if (Objects.isNull(data)) {
            throw new ResolverException("json解析异常");
        }
        List<MovieBean> rs = data.toJavaList(MovieBean.class);
        return rs;
    }

    public static List<MovieBean> resolve(InputStream inputStream) {
        StringBuilder json = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            json.append(scanner.nextLine());
            json.append("\n");
        }

        return resolve(json.toString());
    }

}
