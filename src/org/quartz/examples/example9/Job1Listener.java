package org.quartz.examples.example9;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 20, 2014 : 10:13:08 PM
 */
public class Job1Listener implements JobListener{

	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example9.Job1Listener.class);
	
	public String getName() {
		return "job1_to_job2";
	}

	public void jobToBeExecuted(JobExecutionContext inContext) {
		_log.info("Job1Listener says: Job Is about to be executed.");
	}

	public void jobExecutionVetoed(JobExecutionContext inContext) {
		_log.info("Job1Listener says: Job Execution was vetoed.");
	}

	public void jobWasExecuted(JobExecutionContext inContext, JobExecutionException inException) {
		_log.info("Job1Listener says: Job was executed.");
		JobDetail job2 = JobBuilder.newJob(org.quartz.examples.example9.SimpleJob2.class).withIdentity("job2").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("job2Trigger").startNow().build();
		try {
			inContext.getScheduler().scheduleJob(job2, trigger);
		} catch (SchedulerException e) {
			_log.warn("Unable to schedule job2!");
			e.printStackTrace();
		}
	}


}
