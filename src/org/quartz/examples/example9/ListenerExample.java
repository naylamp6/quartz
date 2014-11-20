package org.quartz.examples.example9;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 20, 2014 : 10:15:10 PM
 */
public class ListenerExample {

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(org.quartz.examples.example9.ListenerExample.class);
		log.info("------- Initializing ----------------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete -----------");
		log.info("------- Scheduling Jobs -------------------");
		JobDetail job = JobBuilder.newJob(org.quartz.examples.example9.SimpleJob1.class).withIdentity("job1").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1").startNow().build();
		JobListener listener = new Job1Listener();
		Matcher matcher = KeyMatcher.keyEquals(job.getKey());
		sched.getListenerManager().addJobListener(listener, matcher);
		sched.scheduleJob(job, trigger);
		log.info("------- Starting Scheduler ----------------");
		sched.start();
		log.info("------- Waiting 30 seconds... --------------");
		try {
			Thread.sleep(30000L);
		} catch (Exception e) {
		}
		log.info("------- Shutting Down ---------------------");
		sched.shutdown(true);
		log.info("------- Shutdown Complete -----------------");
		SchedulerMetaData metaData = sched.getMetaData();
		log.info((new StringBuilder()).append("Executed ").append(metaData.getNumberOfJobsExecuted()).append(" jobs.").toString());
	}

	public static void main(String args[]) throws Exception {
		ListenerExample example = new ListenerExample();
		example.run();
	}
}
