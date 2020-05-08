package com.lsl.demo.utils.global;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 全局的信息类
 * @author lisiliang
 * @since 2020/1/10
 */
public class BaseContextHandler {

    public  static final ThreadLocal<Map<String, Object>> local = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = local.get();
        if (Objects.isNull(map)) {
            map = new HashMap<>();
            local.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = local.get();
        if (Objects.isNull(map)) {
            map = new HashMap<>();
            local.set(map);
        }
        return map.get(key);
    }

    public static boolean isLogin() {
        return Objects.nonNull(BaseContextHandler.getUserId());
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) BaseContextHandler.get("request");
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) BaseContextHandler.get("response");
    }

    public static void clear(){
        local.remove();
    }

    public static String getUserId() {
        return returnStr(get("userId"));
    }

    public static void setUserId(String userId) {
        set("userId", userId);
    }

    public static String getUserType() {
        return returnStr(get("userType"));
    }

    private static String returnStr(Object obj) {
        return Objects.isNull(obj) ? null : obj.toString();
    }

}
