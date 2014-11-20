package org.quartz.examples.example9;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 20, 2014 : 10:17:44 PM
 */
public class SimpleJob2 implements  Job{

	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example9.SimpleJob2.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		org.quartz.JobKey jobKey = context.getJobDetail().getKey();
		_log.info((new StringBuilder()).append("SimpleJob2 says: ").append(jobKey).append(" executing at ").append(new Date()).toString());
	}

}
