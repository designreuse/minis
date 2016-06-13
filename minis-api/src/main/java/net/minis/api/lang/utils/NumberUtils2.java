package net.minis.api.lang.utils;


public class NumberUtils2 {

    public static int defaultValue(Integer value) {
        return defaultValue(value, 0);
    }

    public static int defaultValue(Integer value, int defaultValue) {

        if (value != null) {
            return value.intValue();
        }

        return defaultValue;
    }

}
