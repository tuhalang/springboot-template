package com.example.base.api.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class MasterConnectionAspect {

    private static final ThreadLocal<Boolean> masterConnection = new ThreadLocal<>();

    public static boolean isMasterConnection() {
        if (masterConnection.get() ==  null) {
            return false;
        }

        return masterConnection.get();
    }

    @Around("@annotation(com.example.base.api.config.datasource.MasterConnection)")
    public Object extractTransactionalMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        MasterConnection masterConnectionAnnotation = method.getAnnotation(MasterConnection.class);

        if (masterConnectionAnnotation != null) {
            log.info("masterConnection: {}", method.getName());
            masterConnection.set(true);
        }

        try {
            return joinPoint.proceed();
        } finally {
            masterConnection.remove();
        }
    }
}
