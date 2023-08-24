package com.tct.springboot.utils;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.context.MessageSource;

import java.util.Locale;

public abstract class MessageAccessor {
    protected MessageSource messageSource;

    public String getMessage(@Nonnull Locale locale, @Nonnull String key, @Nullable Object... parameter) {
        return messageSource.getMessage(key, parameter, locale);
    }

    public String getMessage(@Nonnull String key, @Nullable Object... parameter) {
        return messageSource.getMessage(key, parameter, ProjectConstants.DEFAULT_LOCALE);
    }
}
