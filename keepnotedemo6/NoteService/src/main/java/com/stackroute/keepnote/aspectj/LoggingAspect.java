package com.stackroute.keepnote.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggingAspect {
	/*
	 * Write loggers for each of the methods of Note controller, any particular
	 * method will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut(value = "execution(* com.stackroute.keepnote.controller.*.*(..))")
	public void beforeController() {
	}

	@Before("beforeController()")
	public void beforeAspect(JoinPoint joinpoint) {
		logger.info("-------@Before(\"beforeController()\"----------" + joinpoint.getSignature());

	}

	@Pointcut(value = "execution(* com.stackroute.keepnote.controller.*.*(..))")
	public void afterController() {
	}

	@After("afterController()")
	public void afterAspect(JoinPoint joinpoint) {
		logger.info("----------@After(afterController()" + joinpoint.getSignature());
	}

	@Pointcut(value = "execution(* com.stackroute.keepnote.controller.*.*(..))")
	public void afterThrowing() {
	}

	@AfterThrowing(pointcut = "execution(* com.stackroute.keepnote.controller.*.*(..))", throwing = "exception")
	public void afterThrowingAspect(Exception exception) {
		logger.info("----@AfterThrowing---" + exception.getMessage());
	}

	@Pointcut(value = "execution(* com.stackroute.keepnote.controller.*.*(..))")
	public void afterReturning() {
	}

	@AfterReturning(pointcut = "execution(* com.stackroute.keepnote.controller.*.*(..))", returning = "val")
	public void afterReturningAspect(Object val) {
		logger.info("----@AfterReturning---" + val);
	}
}
