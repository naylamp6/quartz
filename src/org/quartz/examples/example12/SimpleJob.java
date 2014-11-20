package org.quartz.examples.example12;

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
 * @time Nov 20, 2014 : 10:25:06 PM
 */
public class SimpleJob implements  Job{

	public static final String MESSAGE = "msg";
	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example12.SimpleJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		org.quartz.JobKey jobKey = context.getJobDetail().getKey();
		String message = (String) context.getJobDetail().getJobDataMap().get("msg");
		_log.info((new StringBuilder()).append("SimpleJob: ").append(jobKey).append(" executing at ").append(new Date()).toString());
		_log.info((new StringBuilder()).append("SimpleJob: msg: ").append(message).toString());
	}

}
