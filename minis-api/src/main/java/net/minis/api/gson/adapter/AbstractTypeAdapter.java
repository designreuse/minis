package net.minis.api.gson.adapter;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public abstract class AbstractTypeAdapter<T> 
        implements TypeAdapter, JsonSerializer<T>, JsonDeserializer<T> {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
