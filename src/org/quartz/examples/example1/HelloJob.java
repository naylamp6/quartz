package org.quartz.examples.example1;

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
 * @time Nov 15, 2014 : 8:33:04 PM
 */
public class HelloJob implements Job {
	private static Logger logger = LoggerFactory.getLogger(org.quartz.examples.example1.HelloJob.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info((new StringBuilder()).append("Hello World! - ").append(new Date()).toString());
	}

}
