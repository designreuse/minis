package net.minis.api.lang.utils;

import static java.lang.String.format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.ReflectionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * The class property utilities.
 * 
 * @author yen.
 */
@Slf4j
public class PropertyUtils2 {

    /**
     * Close Constructor.
     */
    private PropertyUtils2() {
    }

    /**
     * Retrieving fields list of specified class If recursively is true,
     * retrieving fields from all class hierarchy.
     * 
     * @param bean
     *            - where fields are searching.
     * @param recursively
     *            - to recursively searching fields.
     * 
     * @return list of fields
     */
    @SuppressWarnings("rawtypes")
    public static Field[] getDeclaredFields(Class bean, boolean recursively) {

        List<Field> fields = new LinkedList<Field>();

        Field[] declaredFields = bean.getDeclaredFields();

        Collections.addAll(fields, declaredFields);

        Class superClass = bean.getSuperclass();

        if (superClass != null && recursively) {

            Field[] declaredFieldsOfSuper = 
                    getDeclaredFields(superClass, recursively);

            if (declaredFieldsOfSuper.length > 0) {
                Collections.addAll(fields, declaredFieldsOfSuper);
            }
        }

        return fields.toArray(new Field[fields.size()]);
    }

    /**
     * Retrieving fields list of specified class If recursively is true,
     * retrieving fields from all class hierarchy.
     * 
     * @param bean
     *            - where fields are searching.
     * @param recursively
     *            - to recursively searching fields.
     * 
     * @return list of fields
     */
    public static Field[] getDeclaredFields(Object bean, boolean recursively) {
        return getDeclaredFields(bean.getClass(), recursively);
    }

    /**
     * Retrieving fields list of specified class and which are annotated by
     * incoming annotation class If recursively is true, retrieving fields from
     * all class hierarchy
     * 
     * @param bean
     *            - where fields are searching
     * @param annotationClass
     *            - specified annotation class
     * @param recursively
     *            - to recursively searching fields.
     * 
     * @return list of annotated fields
     */
    @SuppressWarnings("rawtypes")
    public static Field[] getAnnotatedDeclaredFields(Class bean,
            Class<? extends Annotation> annotationClass, boolean recursively) {

        Field[] allFields = getDeclaredFields(bean, recursively);

        List<Field> annotatedFields = new LinkedList<Field>();

        for (Field field : allFields) {
            if (field.isAnnotationPresent(annotationClass)) {
                annotatedFields.add(field);
            }
        }

        return annotatedFields.toArray(new Field[annotatedFields.size()]);
    }

    /**
     * Retrieving fields list of specified class and which are annotated by
     * incoming annotation class If recursively is true, retrieving fields from
     * all class hierarchy
     * 
     * @param bean
     *            - where fields are searching
     * @param annotationClass
     *            - specified annotation class
     * @param recursively
     *            - to recursively searching fields.
     * 
     * @return list of annotated fields
     */
    public static Field[] getAnnotatedDeclaredFields(Object bean,
            Class<? extends Annotation> annotationClass, boolean recursively) {

        return getAnnotatedDeclaredFields(
                bean.getClass(), annotationClass, recursively);
    }

    /**
     * Set field value to annotated field.
     * 
     * @param target
     *            - the bean object instance.
     * @param annotationClass
     *            - the annotation class
     * @param value
     *            - the field value.
     * 
     * @throws IllegalAccessException
     *             if invoke failure to throw this exception.
     */
    public static void setAnnotatedFieldValue(Object target, Object value,
            Class<? extends Annotation> annotationClass) 
                    throws IllegalAccessException {

        Field[] allFields = getDeclaredFields(target, true);

        for (Field field : allFields) {

            if (field.isAnnotationPresent(annotationClass)) {
                setAccessibleFieldValue(target, field, value);

                log.debug(format(
                        "autowire field value to annotated field: \"@%s %s\" success.",
                        annotationClass.getSimpleName(), field.getName()));
            }
        }
    }

    public static Object getAccessibleFieldValue(Object target, String fieldName) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        ReflectionUtils.makeAccessible(field);
        return ReflectionUtils.getField(field, target);
    }

    public static void setAccessibleFieldValue(Object target, String fieldName, Object value) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        setAccessibleFieldValue(target, field, value);
    }

    public static void setAccessibleFieldValue(Object target, Field field, Object value) {
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, target, value);
    }

}
