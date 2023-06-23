package com.example.tracking.trackinglibrary.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitoringAspect {
	private static final Logger logger = LoggerFactory.getLogger(MonitoringAspect.class);

	@Around("@annotation(Monitor)")
	public Object monitorMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		String methodName = joinPoint.getSignature().toShortString();

		// Log method entry
		logger.info("Entering method: {}", methodName);

		// Proceed with the original method execution
		Object result = joinPoint.proceed();

		// Log method exit
		long executionTime = System.currentTimeMillis() - startTime;
		logger.info("Exiting method: {}. Execution time: {} ms", methodName, executionTime);

		return result;
	}
}
