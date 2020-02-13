package com.lsl.demo.first.Utils.token;

/**
 * @author lisiliang
 * @since 2020/1/11
 */
public class TokenBuilder {

    public static Token buildToken(String token) {
        Token t = new Token();
        t.setToken(token);
        return t;
    }

}
