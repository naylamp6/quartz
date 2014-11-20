package org.quartz.examples.example11;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 20, 2014 : 10:22:31 PM
 */
public class SimpleJob implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		_log.info((new StringBuilder()).append("Executing job: ").append(jobKey).append(" executing at ").append(new Date()).toString());
		long delayTime = context.getJobDetail().getJobDataMap().getLong("delay time");
		try {
			Thread.sleep(delayTime);
		} catch (Exception e) {
		}
		_log.info((new StringBuilder()).append("Finished Executing job: ").append(jobKey).append(" at ").append(new Date()).toString());
	}

	private static Logger _log = LoggerFactory.getLogger( org.quartz.examples.example11.SimpleJob.class);
	public static final String DELAY_TIME = "delay time";

}
