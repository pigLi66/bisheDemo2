package com.lsl.demo.first.utils.token;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lisiliang
 * @since 2020/1/10
 */
@Data
@NoArgsConstructor
public class Token {

    public static final String HEADER_TOKEN = "token";
    public static final long DEFAULT_TIME_OUT = 86400000;

    private String token;
    private String userId;
    private Date date;

    static final AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, "0CoJUm6Qyw8W8jud".getBytes(), "0909090405060708".getBytes());

    public Token(String userId) {
        this(userId, new Date(System.currentTimeMillis() + DEFAULT_TIME_OUT));
    }

    public Token(String userId, Date date) {
        this.userId = userId;
        this.date = date;
        enToken();
    }

    public void setToken(String token) {
        this.token = token;
        deToken();
    }

    public Date getDate() {
        return this.date;
    }

    public String getUserId() {
        return this.userId;
    }

    private void enToken() {
        String s = this.userId + ":" + Long.toString(this.date.getTime());
        this.token = aes.encryptHex(s);
    }

    private void deToken() {
        String[] s = aes.decryptStr(token, CharsetUtil.CHARSET_UTF_8).split(":");
        this.userId = s[0];
        this.date = new Date(Long.parseLong(s[1]));
    }

    public void refresh() {
        refresh(new Date(System.currentTimeMillis() + DEFAULT_TIME_OUT));
    }

    public void refresh(Date date) {
        this.date = date;
        deToken();
    }

}
