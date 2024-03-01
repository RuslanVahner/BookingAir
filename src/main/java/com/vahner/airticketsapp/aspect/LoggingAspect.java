package com.vahner.airticketsapp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

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
        MDC.put("requestId", generateRequestId());
        log.info("Incoming request to Controller method: {}.{}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Метод, который выполняется перед вызовом метода в сервисе.
     * log.info выводит информацию о выполнении сервисного метода.
     */
    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        Object[] args = jp.getArgs();
        log.info("Executing service method: {}.{} with args: {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(args));
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

    @After("controllerLog()")
    public void doAfterController() {
        MDC.clear();
    }
}