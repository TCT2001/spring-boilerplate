package com.tct.springboot.exceptions;

import com.tct.springboot.CommonResponse;
import com.tct.springboot.enums.StatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(CommonException.class)
    ResponseEntity<CommonResponse<Object>> handleCommonException(CommonException exception) {
        return ResponseEntity.ok().body(CommonResponse
                .builder()
                .code(StatusCode.EXCEPTION)
                .description(exception.getMessage())
                .build());
    }
}
