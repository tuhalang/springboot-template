package com.example.base.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
