package org.quartz.examples.example15;

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
 * @time Nov 20, 2014 : 10:34:52 PM
 */
public class SimpleRecoveryJob implements  Job{

	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example15.SimpleRecoveryJob.class);
	private static final String COUNT = "count";
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		if (context.isRecovering())
			_log.info((new StringBuilder()).append("SimpleRecoveryJob: ").append(jobKey).append(" RECOVERING at ").append(new Date()).toString());
		else
			_log.info((new StringBuilder()).append("SimpleRecoveryJob: ").append(jobKey).append(" starting at ").append(new Date()).toString());
		long delay = 10000L;
		try {
			Thread.sleep(delay);
		} catch (Exception e) {
		}
		JobDataMap data = context.getJobDetail().getJobDataMap();
		int count;
		if (data.containsKey("count"))
			count = data.getInt("count");
		else
			count = 0;
		count++;
		data.put("count", count);
		_log.info((new StringBuilder()).append("SimpleRecoveryJob: ").append(jobKey).append(" done at ").append(new Date()).append("\n Execution #").append(count).toString());
	}

}
