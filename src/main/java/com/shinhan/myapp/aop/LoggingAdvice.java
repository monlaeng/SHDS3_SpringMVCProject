package com.shinhan.myapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//보조업무(cross cutting concern)
@Component
//@Aspect	//AOP를 Annotation으로 이용하기 (@Aspect, advice = 보조업무(advice) + pointcut)
public class LoggingAdvice {
	
	@Pointcut("execution(* selectAll())")
	public void targetMethod2() {}

	@Pointcut("within(com.shinhan.myapp.model.BoardService)")
	public void targetMethod3() {}
	
	@Before("targetMethod2()")
	public void f_before() {
		System.out.println("--------@Before----------");
	}
	
	@After("targetMethod2()")
	public void f_after() {
		System.out.println("--------@After----------");
	}
	
	@AfterReturning("targetMethod2()")
	public void f_afterReturn() {
		System.out.println("--------@AfterReturning----------");
	}
	
	//target의 주업무 수행 전후 
	@Around("targetMethod2()")
	public Object arroundMethod(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("!!!! : LogginAdvice around()");
		System.out.println(jp.getSignature().getName() + "메서드 호출 전");

		//주업무를 수행한다. (주의!!!)
		Object object = jp.proceed();

		//주업무 수행후 돌아와서 수행한다. 
		System.out.println("!!!!!!: loggingAdvice around()");
		System.out.println(jp.getSignature().getName() + "메서드 호출 후");
		return object;
	}
}