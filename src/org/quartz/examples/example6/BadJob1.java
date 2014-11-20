package org.quartz.examples.example6;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 17, 2014 : 3:26:51 PM
 */
public class BadJob1 implements Job{

	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example6.BadJob1.class);
	private int calculation;
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		int denominator = dataMap.getInt("denominator");
		_log.info((new StringBuilder()).append("---").append(jobKey).append(" executing at ").append(new Date()).append(" with denominator ").append(denominator).toString());
		try {
			calculation = 4815 / denominator;
		} catch (Exception e) {
			_log.info("--- Error in job!");
			JobExecutionException e2 = new JobExecutionException(e);
			dataMap.put("denominator", "1");
			e2.setRefireImmediately(true);
			throw e2;
		}
		_log.info((new StringBuilder()).append("---").append(jobKey).append(" completed at ").append(new Date()).toString());
	}

}
