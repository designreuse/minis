package net.minis.api.lang.utils;

import java.util.Set;

public class SetsUtils2 {

    public static <E> E find(Set<E> elements, E object) {
        for (E element : elements) {
            if (element.equals(object)) {
                return element;
            }
        }
        return null;
    }

    public static <E> E findOrCreate(Set<E> elements, E object) {
        E result = find(elements, object);
        return result != null ? result : object;
    }

}
