/**
 * 
 */
package com.alt.shop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Syedyasiraswath
 *
 */
@Component
@Aspect
public class LoggingAspect {

	@Before("execution(* com.alt.shop.controller.*.*(..))")
	public void enterLogForController(JoinPoint joinPoint) {
		Class<? extends Signature> classNm = joinPoint.getSignature().getClass();
		String method = joinPoint.getSignature().getName();
		String declaringType = joinPoint.getSignature().getDeclaringTypeName();
		log("Entry details :"+classNm +" : "+ declaringType+" : "+method);
	}
	
	@AfterThrowing(pointcut = "execution(* com.alt.shop.*.*.*(..))", throwing = "ex")
	public void exLogForController(JoinPoint joinPoint, Throwable ex) throws Throwable {
		Class<? extends Signature> classNm = joinPoint.getSignature().getClass();
		String method = joinPoint.getSignature().getName();
		String declaringType = joinPoint.getSignature().getDeclaringTypeName();
		log("Exception caught @"+classNm +" : "+ declaringType+" : "+method+" : "+ex.getMessage());
	}
	
	private void log(String msg) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info(msg);
	}
}
 