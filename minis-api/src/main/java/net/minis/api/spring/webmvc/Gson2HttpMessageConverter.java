package net.minis.api.spring.webmvc;

import net.minis.api.gson.adapter.MultiDateTimeAdapter;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;

import com.google.gson.GsonBuilder;

@Component
public class Gson2HttpMessageConverter extends GsonHttpMessageConverter {

    public Gson2HttpMessageConverter() {

        MultiDateTimeAdapter dateTimeAdapter = new MultiDateTimeAdapter();

        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(java.sql.Date.class, dateTimeAdapter)
                .registerTypeAdapter(java.util.Date.class, dateTimeAdapter)
                .setPrettyPrinting()
                .serializeNulls();

        this.setGson(builder.create());
    }

}
