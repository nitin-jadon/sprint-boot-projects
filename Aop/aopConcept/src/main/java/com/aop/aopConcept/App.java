package com.aop.aopConcept;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aop.aopConcept.paymentService.PaymentService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// provide bean config file path
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/aop/aopConcept/config.xml");
    	// get bean with bean name and type of class
    	PaymentService service = context.getBean("payment", PaymentService.class);
    	//execute methods of that class
    	service.makePayment();
    	System.out.println("first payment done");
    	service.makePayment1(123);
    }
}
