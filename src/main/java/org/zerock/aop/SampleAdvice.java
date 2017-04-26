package org.zerock.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component //이도 저도 아닐때 붙인다.
@Aspect //추상명사
public class SampleAdvice {
	
	@Before("execution( * org.zerock.service.SampleService*.doA(..))")
	public void startLog(){
		
		System.out.println("======================================");
		System.out.println("======================================");
		
	}
	
	@Around("execution( * org.zerock.service.SampleService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp)throws Throwable{
		
		long start =System.currentTimeMillis();
		System.out.println("check time start");
		
		Object result = pjp.proceed();
		long end =System.currentTimeMillis();
		System.out.println("check time end");
		System.out.println("total: "+(end-start));
		
		return result;
	}
	
}
