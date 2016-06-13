package net.minis.api.gson.adapter;

import java.lang.reflect.Type;
import java.util.Date;

import org.joda.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

/**
 * 
 * @author yen.
 */
public abstract class AbstractDateTimeTypeAdapter extends AbstractTypeAdapter<Date> {

    private DateTimeFormatter formatter;

    private Type[] types = { java.util.Date.class, java.sql.Date.class };

    protected AbstractDateTimeTypeAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Type[] getTypes() {
        return types;
    };

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return formatter.parseDateTime(json.getAsString()).toDate();
    }

    @Override
    public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.print(date.getTime()));
    }

}
