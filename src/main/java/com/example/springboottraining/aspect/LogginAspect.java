package com.example.springboottraining.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Log
@Component
public class LogginAspect {

//    @Before("within(com.example.springboottraining..*) && (bean(*Controller) || bean(*Service) || bean(Repository))")
//    public void beforeMeasure(JoinPoint joinPoint) {
//        log.info("Before method execution " + joinPoint.getSignature().getName());
//    }

    @Around("execution(* com.example.springboottraining.controller.ToDoController.*(..))")
    public Object aroundTodoController(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before method execution [controller] " + joinPoint.getSignature().getName());
        long start = System.currentTimeMillis();
        Object res = joinPoint.proceed();
        log.info("After method execution [controller] " + joinPoint.getSignature().getName());
        long end = System.currentTimeMillis();

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        long difference = end - start;
        log.info(String.format("Class: %s, Method: %s, Difference: %d ms", className, methodName, difference));
        return res;
    }

    @Around("execution(* com.example.springboottraining.repository.ToDoRepository.*(..))")
    public Object aroundTodoRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before method execution [repository] " + joinPoint.getSignature().getName());
        Object res = joinPoint.proceed();
        log.info("After method execution [repository] " + joinPoint.getSignature().getName());
        return res;
    }

    @Around("execution(* com.example.springboottraining.service.ToDoServiceImpl.*(..))")
    public Object aroundTodoService(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before method execution [service] " + joinPoint.getSignature().getName());
        Object res = joinPoint.proceed();
        log.info("After method execution [service] " + joinPoint.getSignature().getName());
        return res;
    }

    @Around("execution(* com.example.springboottraining.entity.ToDo.*(..))")
    public Object aroundTodoEntity(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before method execution [entity] " + joinPoint.getSignature().getName());
        Object res = joinPoint.proceed();
        log.info("After method execution [entity] " + joinPoint.getSignature().getName());
        return res;
    }



}
