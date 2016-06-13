package net.minis.api.gson.adapter;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatterBuilder;

public class SimpleDateTimeAdapter extends AbstractDateTimeTypeAdapter {

    public SimpleDateTimeAdapter(String datePattern) {
        super(new DateTimeFormatterBuilder().append(DateTimeFormat
                .forPattern(datePattern).getParser()).toFormatter());
    }

}
