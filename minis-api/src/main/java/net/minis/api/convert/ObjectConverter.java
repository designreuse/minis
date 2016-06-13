package net.minis.api.convert;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.util.Collection;
import java.util.List;

import net.minis.api.lang.utils.ClassUtils2;

import org.modelmapper.ModelMapper;

import com.google.common.collect.Lists;

/**
 * The Object Converter.
 * 
 * @author yen.
 */
abstract public class ObjectConverter {

    protected ModelMapper mapper = new ModelMapper();

    protected void autoMapping(Object source, Object target) {
        mapper.map(source, target);
    }

    /**
     * convert sources object to target objects.
     * 
     * @param sources
     *            - the collection of source objects.
     * 
     * @return the list of target objects.
     */
    final public <S, T> List<T> convert(Collection<S> sources) {

        if (sources == null) {
            return null;
        }

        if (isEmpty(sources)) {
            return Lists.newArrayList();
        }

        List<T> targets = Lists.newArrayList();

        for (S source : sources) {

            T target = null;

            if (source != null) {
                target = ClassUtils2.invokeMethod(this, "convert", source);
            }

            targets.add(target);
        }

        return targets;
    }


    /**
     * Convert source objects to target objects.
     * 
     * @param sources
     *            - the collection of source objects.
     * @param objectFactory
     *            - the target object factory.
     * 
     * @return the list of target model
     */
    final public <S, T> List<T> convert(Collection<S> sources, 
            ObjectFactory<S, T> objectFactory) {

        if (sources == null) {
            return null;
        }

        if (isEmpty(sources)) {
            return Lists.newArrayList();
        }

        List<T> targets = Lists.newArrayList();

        for (S source : sources) {

            T target = null;

            if (source != null) {
                target = ClassUtils2.invokeMethod(
                        this, "convert", source, objectFactory);
            }

            targets.add(target);
        }

        return targets;
    }

}
