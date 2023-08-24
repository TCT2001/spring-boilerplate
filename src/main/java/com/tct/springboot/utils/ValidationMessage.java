package com.tct.springboot.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ValidationMessage extends MessageAccessor {
    public ValidationMessage(@Qualifier("validationMessageSource") MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
