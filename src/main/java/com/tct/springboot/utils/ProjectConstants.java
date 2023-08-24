package com.tct.springboot.utils;

import java.nio.charset.StandardCharsets;
import java.util.Locale;


public final class ProjectConstants {
    public static final String USER_DB_UNIT_NAME = "USER";
    public static final String PRODUCT_DB_UNIT_NAME = "PRODUCT";

    public static final String DEFAULT_ENCODING = StandardCharsets.UTF_8.name();
    public static final Locale VIETNAM_LOCALE = new Locale.Builder().setLanguage("vi").setRegion("VN").build();
    public static final Locale ENGLISH_LOCALE = new Locale.Builder().setLanguage("en").setRegion("US").build();
    public static volatile Locale DEFAULT_LOCALE = VIETNAM_LOCALE;

    private ProjectConstants() {
        throw new UnsupportedOperationException();
    }

    public enum SupportedLocale {
        VN(VIETNAM_LOCALE),
        EN(ENGLISH_LOCALE);

        private Locale locale;

        SupportedLocale(Locale locale) {
            this.locale = locale;
        }

        public Locale getLocale() {
            return locale;
        }
    }

}
