package org.quartz.examples.example3;

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
 * @time Nov 15, 2014 : 9:29:20 PM
 */
public class  SimpleJob implements Job{

	private static Logger log = LoggerFactory.getLogger(org.quartz.examples.example3.SimpleJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		org.quartz.JobKey jobKey = context.getJobDetail().getKey();
		log.info((new StringBuilder()).append("SimpleJob says: ").append(jobKey).append(" executing at ").append(new Date()).toString());
	}

}
