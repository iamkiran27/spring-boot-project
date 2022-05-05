package com.example.demo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public  void controllerPointCut()
    {

    }

    @Pointcut("execution(* com.example.demo.services.*.*(..))")
    public  void servicePointCut()
    {

    }

    @Pointcut("execution(* com.example.demo.dao.*.*(..))")
    public  void daoPointCut()
    {

    }

    @Pointcut("controllerPointCut() || servicePointCut() ||  daoPointCut()")
    public  void all()
    {

    }


    @Before("all()")
    public  void  executeBefore(JoinPoint joinPoint)
    {
        String method = joinPoint.getSignature().toShortString();

        System.out.println("@before calling method : "+method);

        Object[] objects = joinPoint.getArgs();

        for(Object temp:objects)
        {
            System.out.println("Result : "+temp);
        }

    }


    @AfterReturning(pointcut = "all()", returning = "result")
    public  void executeAfter(JoinPoint joinPoint, Object result)
    {
        String method = joinPoint.getSignature().toShortString();

        System.out.println("@after calling method : "+method);

        System.out.println("Result is : "+result);

    }





}
