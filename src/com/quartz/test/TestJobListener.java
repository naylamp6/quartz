package com.quartz.test;

import org.quartz.listeners.JobListenerSupport;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 23, 2014 : 8:56:50 PM
 */
public class TestJobListener extends JobListenerSupport {

	@Override
	public String getName() {
		return "myJobListener";
	}

}
