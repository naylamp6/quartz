package org.quartz.examples.example7;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 20, 2014 : 10:07:00 PM
 */
public class InterruptExample {

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(org.quartz.examples.example7.InterruptExample.class);
		log.info("------- Initializing ----------------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete -----------");
		log.info("------- Scheduling Jobs -------------------");
		Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		JobDetail job = JobBuilder.newJob(org.quartz.examples.example7.DumbInterruptableJob.class).withIdentity("interruptableJob1", "group1").build();
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
		Date ft = sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		sched.start();
		log.info("------- Started Scheduler -----------------");
		log.info("------- Starting loop to interrupt job every 7 seconds ----------");
		for (int i = 0; i < 50; i++)
			try {
				Thread.sleep(7000L);
				sched.interrupt(job.getKey());
			} catch (Exception e) {
			}

		log.info("------- Shutting Down ---------------------");
		sched.shutdown(true);
		log.info("------- Shutdown Complete -----------------");
		SchedulerMetaData metaData = sched.getMetaData();
		log.info((new StringBuilder()).append("Executed ").append(metaData.getNumberOfJobsExecuted()).append(" jobs.").toString());
	}

	public static void main(String args[]) throws Exception {
		InterruptExample example = new InterruptExample();
		example.run();
	}
}
