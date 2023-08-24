package com.tct.springboot.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


@Service
public class GeneralMessage extends MessageAccessor {
    public GeneralMessage(@Qualifier("generalMessageSource") MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
