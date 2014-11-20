package org.quartz.examples.example8;

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
 * @time Nov 20, 2014 : 10:11:04 PM
 */
public class SimpleJob implements Job{

	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example8.SimpleJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		org.quartz.JobKey jobKey = context.getJobDetail().getKey();
		_log.info((new StringBuilder()).append("SimpleJob says: ").append(jobKey).append(" executing at ").append(new Date()).toString());
	}

}
