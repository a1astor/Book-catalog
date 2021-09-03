package com.publishing.house.bookcatalog.utils;

//TODO add throw specific exeption by class name
public class NumberUtils {
    public static Long parseStringToLong(String stringId) {
        Long id = null;
        try {
            id = Long.valueOf(stringId);
        } catch (NumberFormatException e) {
            return null;
        }
        return id;
    }
}
