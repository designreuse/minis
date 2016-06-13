package net.minis.api.convert;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import net.minis.api.lang.utils.ClassUtils2;

import org.modelmapper.ModelMapper;

import com.google.common.collect.Lists;

/**
 * The Model Converter.
 * 
 * @author yen.
 */
abstract public class ModelConverter<S, T> {

    protected Class<S> sourceClass;
    protected Class<T> targetClass;
    protected ModelMapper mapper = new ModelMapper();

    /**
     * Convert source model to target model.
     * 
     * @param source
     *            - the source model.
     * @param target
     *            - the target model.
     * @return
     */
    abstract public T convert(S source, T target);

    @SuppressWarnings("unchecked")
    public ModelConverter() {
        ParameterizedType genericType = ClassUtils2.getGenericType(getClass());
        sourceClass = (Class<S>) genericType.getActualTypeArguments()[0];
        targetClass = (Class<T>) genericType.getActualTypeArguments()[1];
    }

    /**
     * Auto mapping all object property.
     * 
     * @param source
     *            - the source models.
     * @param target
     *            - the target models.
     */
    protected void autoMapping(Object source, Object target) {
        mapper.map(source, target);
    }

    /**
     * Convert source model to target model.
     * 
     * @param source
     *            - the source model.
     * @return
     */
    public T convert(S source) {

        T target;

        try {

            target = targetClass.newInstance();

        } catch (Exception e) {
            throw new IllegalStateException("Could new instance, please "
                    + "check target class is can instantiation or not."
                    + targetClass.getName());
        }

        return convert(source, target);
    }

    /**
     * Convert source model to target model. <br/>
     * 
     * you can implement {@link ObjectFactory} to create target model instance.
     * 
     * @param source
     *            - the source model.
     * @param objectFactory
     *            - the target model factory.
     * @return
     */
    public T convert(S source, ObjectFactory<S, T> objectFactory) {
        T target = objectFactory.create(source);
        return convert(source, target);
    }

    /**
     * Convert source models to target models.
     * 
     * @param sources
     *            - the collection of source models.
     * 
     * @return the list of target model
     */
    final public List<T> convert(Collection<S> sources) {

        if (sources == null) {
            return null;
        }

        if (isEmpty(sources)) {
            return Lists.newArrayList();
        }

        List<T> targets = Lists.newArrayList();

        for (S source : sources) {
            T target = convert(source);
            targets.add(target);
        }

        return targets;
    }

    /**
     * convert source object to target model.
     * 
     * @param sources
     *            - the collection of source models.
     * @param objectFactory
     *            - the source object factory.
     * 
     * @return the list of target model
     */
    final public List<T> convert(Collection<S> sources, ObjectFactory<S, T> objectFactory) {

        if (sources == null) {
            return null;
        }

        if (isEmpty(sources)) {
            return Lists.newArrayList();
        }

        List<T> targets = Lists.newArrayList();

        for (S source : sources) {
            T target = convert(source, objectFactory);
            targets.add(target);
        }

        return targets;
    }

}
