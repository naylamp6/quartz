package org.quartz.examples.example14;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriggerEchoJob implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(org.quartz.examples.example14.TriggerEchoJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info((new StringBuilder()).append("TRIGGER: ").append(context.getTrigger().getKey()).toString());
	}

}
