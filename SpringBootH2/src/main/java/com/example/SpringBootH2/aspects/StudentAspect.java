package com.example.SpringBootH2.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.databind.ObjectMapper;





@Aspect
@Component
public class StudentAspect {
	
		//get your logger from sl4j which internally uses logback
		Logger logger=LoggerFactory.getLogger(StudentAspect.class);
		
	//pointcut for methods in controllers package with any modifier, return type and any number of args
	@Pointcut("execution(* com.example.SpringBootH2.controllers.*.*(..))")
	public void forAllmethodsControllers() {}
	
	
	//pointcut for methods in Repositories package with any modifier, return type and any number of args
	@Pointcut("execution(* com.example.SpringBootH2.Repositories.*.*(..))")
	public void forAllmethodsRepositories() {}
	
	
	//pointcut for methods in entities package with any modifier(optional so did not specify with *), any return type (mandatory, so *) and any number of args
	@Pointcut("execution(* com.example.SpringBootH2.entities.*.*(..))")
	public void forAllmethodsEntities() {}
	
	//pointcut declaration for all methods in all packages expect entities package
	@Pointcut("( forAllmethodsControllers() || forAllmethodsRepositories() ) && !forAllmethodsEntities()")
	public void AllMethodsExceptEnties() {}
	
	
	//create an around advice
	@Around("AllMethodsExceptEnties()")
	public Object aroundAllmethods(ProceedingJoinPoint pjp) throws Throwable{
		{	
			ObjectMapper mapper=new ObjectMapper();
			String methodname=pjp.getSignature().getName();
			String classname=pjp.getTarget().getClass().getName();
			Object args[]=pjp.getArgs();
			//executes this before any target method is called
			logger.info("method "+methodname+" called from "+classname+" with args "+mapper.writeValueAsString(args));
			
			//let our advice execute target method using proceeding join point
			Object result=pjp.proceed();
			
			//executes this after our advice executed target method
			logger.info("method "+methodname+" executed from "+classname+" with response "+mapper.writeValueAsString(result));
			
			//As your Around advice is responsible to return the response to the caller, return the result
			return result;
		}
	}
}
