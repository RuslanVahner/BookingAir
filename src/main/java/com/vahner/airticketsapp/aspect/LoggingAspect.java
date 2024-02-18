package com.vahner.airticketsapp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(public * com.vahner.airticketsapp.controller.*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution(public * com.vahner.airticketsapp.service.*.*(..))")
    public void serviceLog() {
    }

    /**
     * Метод, который выполняется перед вызовом метода в контроллере.
     * log.info выводит информацию о запросе, такую как IP-адрес, URL, HTTP-метод и вызываемый метод контроллера.
     */
    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        log.info("Incoming request:\n" +
                        "Controller method: {}.{}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    /**
     * Метод, который выполняется перед вызовом метода в сервисе.
     * log.info выводит информацию о выполнении сервисного метода.
     */
    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        log.info("Executing service method: {}.{}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    /**
     * Метод, который выполняется после успешного выполнения метода в контроллере.
     * log.info выводит информацию о возвращаемом значении метода и завершает обработку запроса.
     */
    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("Method execution successful. Returning: {}", returnObject);
    }

    /**
     * Метод, который выполняется в случае возникновения исключения в методе контроллера.
     * log.error выводит информацию об ошибке и переданном исключении.
     */
    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Exception in controller method: {}.{}. Cause: {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                ex.getMessage());
    }
}