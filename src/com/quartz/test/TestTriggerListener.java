package com.quartz.test;

import org.quartz.listeners.TriggerListenerSupport;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 23, 2014 : 8:57:36 PM
 */
public class TestTriggerListener extends TriggerListenerSupport{

	@Override
	public String getName() {
		  
		return "myTriggerListener";
	}


	
}
