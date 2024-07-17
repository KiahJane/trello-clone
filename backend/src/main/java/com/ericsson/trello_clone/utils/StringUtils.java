package com.ericsson.trello_clone.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static String allLowercase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.toLowerCase();
    }
}
