package net.minis.api.lang.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.ISODateTimeFormat;

public class DateUtils2 {

    private DateUtils2() {
    }

    public static Date convertToDate(String value) {

        if (StringUtils.isBlank(value)) {
            return null;
        }

        DateTimeParser[] dateTimeParsers = {
                ISODateTimeFormat.dateTime().getParser(),
                DateTimeFormat.forPattern("yyyy/MM/dd").getParser(),
                DateTimeFormat.forPattern("yyyy-MM-dd").getParser(), };

        DateTimeFormatter dateTimeFormatter = 
                new DateTimeFormatterBuilder().append(null, dateTimeParsers).toFormatter();

        return dateTimeFormatter.parseDateTime(value).toDate();
    }

}
