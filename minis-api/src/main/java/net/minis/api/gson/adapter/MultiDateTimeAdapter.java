package net.minis.api.gson.adapter;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.lang.reflect.Type;
import java.util.Date;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.ISODateTimeFormat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;

/**
 * 
 * @author yen.
 */
public class MultiDateTimeAdapter extends ISODateTimeAdapter {

    private DateTimeFormatter deserializeFormatter;

    public MultiDateTimeAdapter() {

        DateTimeParser[] dateTimeParsers = { 
                ISODateTimeFormat.dateTime().getParser(), 
                DateTimeFormat.forPattern("yyyy/MM/dd").getParser(),
                DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
        };

        deserializeFormatter = new DateTimeFormatterBuilder().append(null, dateTimeParsers).toFormatter();
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

        String jsonString = json.getAsString();

        if (isNotBlank(jsonString)) {
            return deserializeFormatter.parseDateTime(jsonString).toDate();
        }

        return null;
    }

}
