package com.aop.aopConcept.Aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

@org.aspectj.lang.annotation.Aspect
public class Aspect {

	@Before("execution(* com.aop.aopConcept.paymentService.PaymentServiceImpl.makePayment())")
	public void printBefore()
	{
		System.out.println("initializing payment");
	}
	@After("execution(* com.aop.aopConcept.paymentService.PaymentServiceImpl.makePayment())")
	public void printAfter()
	{
		System.out.println("payment done");
	}

	@After("execution(* com.aop.aopConcept.paymentService.PaymentServiceImpl.makePayment1(..))")
	public void printAfterParameterizedMthod()
	{
		System.out.println("second payment done");
	}
}
