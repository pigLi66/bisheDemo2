package com.lsl.demo.model.sys.dto;

import lombok.Data;

/**
 * @author lisiliang
 * @since 2020/2/18
 */
@Data
public class LoginResponse {

    String Token;

    String timeOut;

    String message;

}
