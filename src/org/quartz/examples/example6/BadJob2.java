package org.quartz.examples.example6;

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
 * @time Nov 17, 2014 : 3:28:52 PM
 */
public class BadJob2 implements Job{

	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example6.BadJob2.class);
	private int calculation;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		_log.info((new StringBuilder()).append("---").append(jobKey).append(" executing at ").append(new Date()).toString());
		try {
			int zero = 0;
			calculation = 4815 / zero;
		} catch (Exception e) {
			_log.info("--- Error in job!");
			JobExecutionException e2 = new JobExecutionException(e);
			e2.setUnscheduleAllTriggers(true);
			throw e2;
		}
		_log.info((new StringBuilder()).append("---").append(jobKey).append(" completed at ").append(new Date()).toString());
	}

}
