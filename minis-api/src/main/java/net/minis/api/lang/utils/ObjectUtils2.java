package net.minis.api.lang.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * The object utilities.
 * 
 * @author yen.
 */
public class ObjectUtils2 {

    /**
     * Checks object if is null or <tt>Object.toString()<tt> is empty.
     * 
     * @return <tt>true</tt> = if is null or empty, otherwise <tt>false</tt>
     */
    static public boolean isNullOrEmptyString(Object object) {
        return object == null || StringUtils.isEmpty(object.toString());
    }

    /**
     * Checks object if is null or <tt>Object.toString()<tt> is empty.
     * 
     * @return <tt>true</tt> = if is null or empty, otherwise <tt>false</tt>
     */
    static public boolean isNullOrBlankString(Object object) {
        return object == null || StringUtils.isBlank(object.toString());
    }

}
