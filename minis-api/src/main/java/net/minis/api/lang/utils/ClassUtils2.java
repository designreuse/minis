package net.minis.api.lang.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import lombok.extern.slf4j.Slf4j;
import net.minis.api.exception.IllegalInvocationException;

import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * The class utilities.
 * 
 * @author yen.
 */
@Slf4j
public class ClassUtils2 {

    /**
     * Close Constructor.
     */
    private ClassUtils2() {
    }

    public static boolean isInstance(Object object, Class<?> classType) {
        return classType.isInstance(object);
    }

    public static boolean isNotInstance(Object object, Class<?> classType) {
        return classType.isInstance(object) == false;
    }

    /**
     * Returns a new instance of an object using constructor without arguments.
     * 
     * @param className
     *            the class name
     * 
     * @return a new object instance or null if some error occours (exceptions
     *         are logged)
     */
    public static <T> T newInstance(String className) {
        return newInstance(className, null, null);
    }

    /**
     * Returns a new instance of an object using constructor with arguments
     * running classes as parameter types.
     * 
     * @param className
     *            the class name
     * @param args
     *            constructor initialization arguments
     * 
     * @return a new object instance or null if some error occours (exceptions
     *         are logged)
     */
    public static <T> T newInstance(String className, Object... args) {
        return newInstance(className, args, null);
    }

    /**
     * Returns a new instance of an object using constructor with specified
     * parameter types and arguments.
     * 
     * @param className
     *            the class name
     * @param args
     *            constructor initialization arguments
     * @param parameterTypes
     *            constructor parameter types
     * 
     * @return a new object instance or null if some error occours (exceptions
     *         are logged)
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className, Object args[], Class<?>[] parameterTypes) {

        try {

            return (T) newInstance(Class.forName(className), args, parameterTypes);

        } catch (ClassNotFoundException e) {
            log.error("Could not found class: " + className, e);
            return null;
        }
    }

    /**
     * Returns a new instance of an object using constructor without arguments.
     * 
     * @param clazz
     *            object class
     * 
     * @return a new object instance or null if some error occours (exceptions
     *         are logged)
     */
    public static <T> T newInstance(Class<T> clazz) {
        return newInstance(clazz, null, null);
    }

    /**
     * Returns a new instance of an object using constructor with arguments
     * running classes as parameter types.
     * 
     * @param clazz
     *            object class
     * @param args
     *            constructor initialization arguments
     * 
     * @return a new object instance or null if some error occours (exceptions
     *         are logged)
     */
    public static <T> T newInstance(Class<T> clazz, Object... args) {
        return newInstance(clazz, args, null);
    }

    /**
     * Returns a new instance of an object using constructor with specified
     * parameter types and arguments.
     * 
     * @param clazz
     *            object class
     * @param args
     *            constructor initialization arguments
     * @param parameterTypes
     *            constructor parameter types
     * 
     * @return a new object instance or null if some error occours (exceptions
     *         are logged)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T newInstance(Class<T> clazz, Object args[], Class<?>[] parameterTypes) {

        try {

            if (args == null) {
                args = new Object[] {};
            }

            if (parameterTypes == null) {
                parameterTypes = getParameterTypes(args);
            }

            Constructor constructor = clazz.getConstructor(parameterTypes);

            if (constructor != null) {
                return (T) constructor.newInstance(args);
            }

        } catch (Exception exception) {
            log.error("Could not create instance.", exception);
            return null;
        }

        return null;
    }

    public static <T> T invokeMethod(Object object, String methodName) {
        return invokeMethod(object, methodName, null, null);
    }

    public static <T> T invokeMethod(Object object, String methodName, Object... args) {
        return invokeMethod(object, methodName, args, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) {

        try {

            if (args == null) {
                args = new Object[] {};
            }

            if (parameterTypes == null) {
                parameterTypes = getParameterTypes(args);
            }

            return (T) MethodUtils.invokeMethod(object, methodName, args, parameterTypes);

        } catch (NoSuchMethodException e) {

            String message = String.format("Could not found method: %s.%s(%s).", 
                    getSimpleClassName(object), methodName, getParameterNames(parameterTypes));

            throw new IllegalInvocationException(message, e);

        } catch (IllegalAccessException e) {

            String message = String.format("Could not access method: %s.%s(%s).", 
                    getSimpleClassName(object), methodName, getParameterNames(parameterTypes));

            throw new IllegalInvocationException(message, e);

        } catch (InvocationTargetException e) {

            String message = String.format("Invoke method failure: %s.%s(%s).", 
                    getSimpleClassName(object), methodName, getParameterNames(parameterTypes));

            throw new IllegalInvocationException(message, e);
        }

    }

    public static ParameterizedType getGenericType(Class<?> clazz) {

        if (clazz == null) {
            return null;
        }

        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            return (ParameterizedType) type;
        }

        return getGenericType((Class<?>) type);
    }

    public static Class<?>[] getParameterTypes(Object[] args) {

        if (args == null) {
            args = new Object[] {};
        }

        int arguments = args.length;
        Class<?>[] parameterTypes = new Class[arguments];

        for (int i = 0; i < arguments; i++) {
            if (args[i] != null) {
                parameterTypes[i] = args[i].getClass();
            } else {
                parameterTypes[i] = null;
            }
        }

        return parameterTypes;
    }

    public static String getSimpleClassName(Object object) {

        if (object instanceof Class) {
            return getSimpleClassName((Class<?>) object);
        }

        return getSimpleClassName(object.getClass());
    }

    public static String getSimpleClassName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    public static String getParameterNames(Class<?>[] parameterTypes) {

        StringBuffer result = new StringBuffer();

        for (Class<?> type : parameterTypes) {
            result.append(type.getSimpleName() + ", ");
        }

        int endIndex = result.length() <= 0 ? 0 : (result.length() - 2);
        return result.substring(0, endIndex);
    }

}
