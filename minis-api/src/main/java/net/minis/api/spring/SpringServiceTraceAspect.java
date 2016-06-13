package net.minis.api.spring;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

@Slf4j
@Aspect
public class SpringServiceTraceAspect {

    @Before("execution(* *..*Service.*(..))")
    public void before(JoinPoint joinPoint) throws Throwable {

        Class<?> target = joinPoint.getTarget().getClass();

        if (target.isAnnotationPresent(Service.class)) {

            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Class<?>[] args = method.getParameterTypes();
            
            if (log.isTraceEnabled()) {
                trace(target, method, args);

            } else if (log.isDebugEnabled()) {
                debug(target, method, args);
            }

        }

    }

    private void trace(Class<?> target, Method method, Class<?>[] args) {

        String className = target.getName();
        String methodName = method.getName();
        StringBuffer argsName = new StringBuffer();

        for (Class<?> object : args) {
            argsName.append(object.getName() + ", ");
        }

        if (argsName.length() > 0) {
            argsName.setLength(argsName.length() - 2);
        }

        log.trace("[Spring Aspect] execute service = {}.{}({}).", new Object[] { className, methodName, argsName });
    }

    private void debug(Class<?> target, Method method, Class<?>[] args) {

        String className = target.getSimpleName();
        String methodName = method.getName();
        StringBuffer argsName = new StringBuffer();

        for (Class<?> object : args) {
            argsName.append(object.getSimpleName() + ", ");
        }

        if (argsName.length() > 0) {
            argsName.setLength(argsName.length() - 2);
        }

        log.debug("[Spring Aspect] execute service = {}.{}({}).", new Object[] { className, methodName, argsName });
    }

}
