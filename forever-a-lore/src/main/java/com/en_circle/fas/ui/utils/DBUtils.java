package com.en_circle.fas.ui.utils;

import org.apache.commons.lang3.StringUtils;

public class DBUtils {

    public static final char DEFAULT_SEPARATOR = ';';
    private static final String[] ESCAPES_SOURCE = new String[] {"\\"};
    private static final String[] ESCAPES_REPLACE = new String[] {"\\\\"};
    private static final String[] REPLACE_LIKE_ESCAPES = new String[] { "\\", "%", "_", "?", "*" };
    private static final String[] REPLACE_LIKE_ESCAPES_WITH = new String[] { "\\\\", "\\%", "\\_", "_", "%" };

    public static String escapeLike(String query) {
        return StringUtils.replaceEach(query, REPLACE_LIKE_ESCAPES, REPLACE_LIKE_ESCAPES_WITH);
    }

}
