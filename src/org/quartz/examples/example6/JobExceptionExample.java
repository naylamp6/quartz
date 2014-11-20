package org.quartz.examples.example6;

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
 * @time Nov 17, 2014 : 3:32:23 PM
 */
public class JobExceptionExample {

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(org.quartz.examples.example6.JobExceptionExample.class);
		log.info("------- Initializing ----------------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete ------------");
		log.info("------- Scheduling Jobs -------------------");
		Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		JobDetail job = JobBuilder.newJob(org.quartz.examples.example6.BadJob1.class).withIdentity("badJob1", "group1").usingJobData("denominator", "0").build();
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();
		Date ft = sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		job = JobBuilder.newJob(org.quartz.examples.example6.BadJob2.class).withIdentity("badJob2", "group1").build();
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
		ft = sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		log.info("------- Starting Scheduler ----------------");
		sched.start();
		log.info("------- Started Scheduler -----------------");
		try {
			Thread.sleep(30000L);
		} catch (Exception e) {
		}
		log.info("------- Shutting Down ---------------------");
		sched.shutdown(false);
		log.info("------- Shutdown Complete -----------------");
		SchedulerMetaData metaData = sched.getMetaData();
		log.info((new StringBuilder()).append("Executed ").append(metaData.getNumberOfJobsExecuted()).append(" jobs.").toString());
	}

	public static void main(String args[]) throws Exception {
		JobExceptionExample example = new JobExceptionExample();
		example.run();
	}
}
