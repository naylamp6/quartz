package org.quartz.examples.example10;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

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
 * @time Nov 20, 2014 : 10:19:54 PM
 */
public class SimpleJob implements  Job{

	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example10.SimpleJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		_log.info((new StringBuilder()).append("Executing job: ").append(jobKey).append(" executing at ").append(new Date()).append(", fired by: ").append(context.getTrigger().getKey()).toString());
		if (context.getMergedJobDataMap().size() > 0) {
			Set keys = context.getMergedJobDataMap().keySet();
			String key;
			String val;
			for (Iterator i$ = keys.iterator(); i$.hasNext(); _log.info((new StringBuilder()).append(" - jobDataMap entry: ").append(key).append(" = ").append(val).toString())) {
				key = (String) i$.next();
				val = context.getMergedJobDataMap().getString(key);
			}

		}
		context.setResult("hello");
	}


}
