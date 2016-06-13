package net.minis.api.gson;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.util.Collection;
import java.util.List;

import net.minis.api.gson.adapter.ISODateTimeAdapter;

import com.google.common.base.Function;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConverter {

    protected GsonBuilder builder;

    private Gson getGson() {
        return builder.create();
    }

    public GsonConverter() {
        this(false);
    }

    public GsonConverter(boolean withoutExpose) {

        builder = new GsonBuilder().serializeNulls().setPrettyPrinting();

        if (withoutExpose) {
            builder.excludeFieldsWithoutExposeAnnotation();
        }
    }

    public void defaultDateFormat() {

        ISODateTimeAdapter dateTimeTypeAdapter = new ISODateTimeAdapter();

        builder.registerTypeAdapter(java.sql.Date.class, dateTimeTypeAdapter)
               .registerTypeAdapter(java.util.Date.class, dateTimeTypeAdapter);
    }

    public String serialize(Object model) {
        return getGson().toJson(model);
    }

    public <T> T deserialize(String json, Class<T> targetClass) {
        return getGson().fromJson(json, targetClass);
    }

    public <T> T convert(Object model, Class<T> clazz) {

        if (model == null) {
            return null;
        }

        String json = serialize(model);
        return deserialize(json, clazz);
    }

    final public <T> List<T> convert(Collection<?> source, final Class<T> targetClass) {

        if (isEmpty(source)) {
            return emptyList();
        }

        Iterable<T> elements = transform(source, new Function<Object, T>() {
            public T apply(final Object input) {
                return convert(input, targetClass);
            }
        });

        return newArrayList(elements);
    }

}
