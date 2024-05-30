package com.shinhan.myapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//��������(Advice) ... �������� ���������� �־����� �־��ش�.
@Component
//@Aspect
public class StopWatchAdvice{
	//com.shinhan.myapp.aop2.Calculator Ŭ���� ���� ��� �޼��忡 StopWatchAdvice ���������� ����ȴ�.
	//�־��� ���Ŀ� StopWatch ����� ����.
	@Pointcut("within(com.shinhan.myapp.controller.BoardController)")
	public void pointcutTarget() {};
	
	
	@Around("pointcutTarget()")
	public Object aa(ProceedingJoinPoint jp) throws Throwable{
		
		//��������
		String methodname = jp.getSignature().getName();
		System.out.println("******" + methodname+ "�޼��� ȣ�� ��");
		StopWatch watch = new StopWatch("���ð�");
		watch.start();
		//�־����� �����Ѵ�. 
		Object object = jp.proceed();
		//��������
		System.out.println("*****" + methodname + "�޼��� ȣ�� ��");
		watch.stop();
		System.out.println("�־��� �ɸ� �ð�:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		return object;
	}
}

