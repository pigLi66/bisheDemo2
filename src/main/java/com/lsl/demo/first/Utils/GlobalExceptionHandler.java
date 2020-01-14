package com.lsl.demo.first.Utils;

import com.lsl.demo.first.Utils.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *     自定义异常处理类
 * </p>
 * @author lisiliang
 * @since 2020/1/11
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public  ResponseEntity<String> handleValidationException(ValidationException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.resolve(e.getCode()) == null ?  HttpStatus.BAD_REQUEST : HttpStatus.resolve(e.getCode()));
    }

}
