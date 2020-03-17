package com.lsl.demo.common.token;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.lsl.demo.common.exceptions.BaseException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.httpclient.HttpStatus;

import java.util.Date;

/**
 * @author lisiliang
 * @since 2020/1/10
 */
@Data
@NoArgsConstructor
public class Token {

    public static final String HEADER_TOKEN = "token";
    public static final Long DEFAULT_TIME_OUT = 86400000L;

    private String token;
    private String userId;
    private Date timeOut;

    static final AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, "0CoJUm6Qyw8W8jud".getBytes(), "0909090405060708".getBytes());

    public Token(String userId) {
        this(userId, new Date(System.currentTimeMillis() + DEFAULT_TIME_OUT));
    }

    public Token(String userId, Date date) {
        this.userId = userId;
        this.timeOut = date;
        enToken();
    }

    public void setToken(String token) {
        this.token = token;
        deToken();
    }

    public Date getTimeOut() {
        return this.timeOut;
    }

    public String getUserId() {
        return this.userId;
    }

    private void enToken() {
        String s = this.userId + ":" + Long.toString(this.timeOut.getTime());
        this.token = aes.encryptHex(s);
    }

    private void deToken() {
        String[] s;
        try {
            s = aes.decryptStr(token, CharsetUtil.CHARSET_UTF_8).split(":");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("token 不合法", HttpStatus.SC_BAD_REQUEST);
        }
        this.userId = s[0];
        this.timeOut = new Date(Long.parseLong(s[1]));
    }

    public void refresh() {
        refresh(new Date(System.currentTimeMillis() + DEFAULT_TIME_OUT));
    }

    public void refresh(Date date) {
        this.timeOut = date;
        enToken();
    }

}
