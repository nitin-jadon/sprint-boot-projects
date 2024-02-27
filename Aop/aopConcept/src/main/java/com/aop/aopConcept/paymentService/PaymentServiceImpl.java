package com.aop.aopConcept.paymentService;

public class PaymentServiceImpl implements PaymentService{

	@Override
	public void makePayment() {
		// TODO Auto-generated method stub
		System.out.println("payment debited");
		System.out.println("payment credited");
	}

	@Override
	public void makePayment1(int value) {
		// TODO Auto-generated method stub
		System.out.println("payment debited");
		System.out.println("payment credited");
	}

}
