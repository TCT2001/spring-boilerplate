package com.tct.springboot.controller;

import com.tct.springboot.CustomMessage;
import com.tct.springboot.aop.LogAOP;
import com.tct.springboot.enums.StatusCode;
import com.tct.springboot.exceptions.CommonException;
import com.tct.springboot.utils.ExceptionMessage;
import com.tct.springboot.utils.ProjectConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ExceptionMessage exceptionMessage;

    @GetMapping(value = "/change-locale")
    @LogAOP
    public ResponseEntity<String> changeLocale(@RequestParam(name = "lang") String supportedLocale) {
        try {
            ProjectConstants.DEFAULT_LOCALE = ProjectConstants.SupportedLocale.valueOf(supportedLocale).getLocale();
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CommonException("Unsupported Language: " + supportedLocale);
        }
        return ResponseEntity.ok(StatusCode.SUCCESS.name());
    }

    @GetMapping("/hello")
    @LogAOP
    public ResponseEntity<String> sayHello(HttpServletRequest request) {
        return ResponseEntity.ok(exceptionMessage.getMessage(CustomMessage.EMAIL_ALREADY_EXISTS));
    }
}
