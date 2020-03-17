package com.lsl.demo.common.token;

/**
 * @author lisiliang
 * @since 2020/1/11
 */
public class TokenBuilder {

    public static Token buildByEncode(String token) {
        Token t = new Token();
        t.setToken(token);
        return t;
    }

}
