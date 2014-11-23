package com.quartz.test;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 23, 2014 : 4:51:26 PM
 */
public class TestJob implements Job {

	@Override
	public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {

		Date fireTime = jobexecutioncontext.getFireTime();
		int count = jobexecutioncontext.getJobDetail().getJobDataMap().getInt("count");
		System.out.println("count : " + count + " ,");
		System.out.print("fireTime : " + fireTime + " , ");
		System.out.println("job content : " + "This is a simple  job");

	}

}
