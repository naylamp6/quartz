package org.quartz.examples.example5;

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
 * @time Nov 17, 2014 : 3:23:48 PM
 */
public class MisfireExample {
	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(org.quartz.examples.example5.MisfireExample.class);
		log.info("------- Initializing -------------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete -----------");
		log.info("------- Scheduling Jobs -----------");
		Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		JobDetail job = JobBuilder.newJob(org.quartz.examples.example5.StatefulDumbJob.class).withIdentity("statefulJob1", "group1").usingJobData("ExecutionDelay", Long.valueOf(10000L)).build();
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();
		Date ft = sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		job = JobBuilder.newJob(org.quartz.examples.example5.StatefulDumbJob.class).withIdentity("statefulJob2", "group1").usingJobData("ExecutionDelay", Long.valueOf(10000L)).build();
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever().withMisfireHandlingInstructionNowWithExistingCount()).build();
		ft = sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		log.info("------- Starting Scheduler ----------------");
		sched.start();
		log.info("------- Started Scheduler -----------------");
		try {
			Thread.sleep(600000L);
		} catch (Exception e) {
		}
		log.info("------- Shutting Down ---------------------");
		sched.shutdown(true);
		log.info("------- Shutdown Complete -----------------");
		SchedulerMetaData metaData = sched.getMetaData();
		log.info((new StringBuilder()).append("Executed ").append(metaData.getNumberOfJobsExecuted()).append(" jobs.").toString());
	}

	public static void main(String args[]) throws Exception {
		MisfireExample example = new MisfireExample();
		example.run();
	}
}
