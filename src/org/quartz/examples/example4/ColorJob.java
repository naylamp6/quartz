package org.quartz.examples.example4;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 17, 2014 : 2:49:08 PM
 */
public class ColorJob implements Job {

	private static Logger log = LoggerFactory.getLogger(org.quartz.examples.example4.ColorJob.class);
	public static final String FAVORITE_COLOR = "favorite color";
	public static final String EXECUTION_COUNT = "count";
	private int counter;

	public ColorJob() {
		counter = 1;
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		org.quartz.JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap data = context.getJobDetail().getJobDataMap();
		String favoriteColor = data.getString("favorite color");
		int count = data.getInt("count");
		log.info((new StringBuilder()).append("ColorJob: ").append(jobKey).append(" executing at ").append(new Date()).append("\n").append("  favorite color is ").append(favoriteColor).append("\n")
				.append("  execution count (from job map) is ").append(count).append("\n").append("  execution count (from job member variable) is ").append(counter).toString());
		count++;
		data.put("count", count);
		counter++;
	}

}
