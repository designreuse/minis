package net.minis.api.convert;

public interface ObjectFactory<S, T> {

    T create(S source);

}
