package org.quartz.examples.example1;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 15, 2014 : 8:22:42 PM
 */
public class SimpleExample {

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(org.quartz.examples.example1.SimpleExample.class);
		log.info("------- Initializing ----------------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete -----------");
		Date runTime = DateBuilder.evenMinuteDate(new Date());
		log.info("------- Scheduling Job  -------------------");
		JobDetail job = JobBuilder.newJob(org.quartz.examples.example1.HelloJob.class).withIdentity("job1", "group1").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
		sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(runTime).toString());
		sched.start();
		log.info("------- Started Scheduler -----------------");
		log.info("------- Waiting 5 seconds... -------------");
		try {
			Thread.sleep(5000L);
		} catch (Exception e) {
		}
		log.info("------- Shutting Down ---------------------");
		sched.shutdown(true);
		log.info("------- Shutdown Complete -----------------");
	}

	public static void main(String args[]) throws Exception {
		SimpleExample example = new SimpleExample();
		example.run();
	}
}
