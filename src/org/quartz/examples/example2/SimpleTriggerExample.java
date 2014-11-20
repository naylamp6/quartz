package org.quartz.examples.example2;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
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
 * @time Nov 15, 2014 : 8:44:07 PM
 */
public class SimpleTriggerExample {

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(org.quartz.examples.example2.SimpleTriggerExample.class);
		log.info("------- Initializing -------------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete --------");
		log.info("------- Scheduling Jobs ----------------");
		Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		JobDetail job = JobBuilder.newJob(org.quartz.examples.example2.SimpleJob.class).withIdentity("job1", "group1").build();
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).build();
		Date ft = sched.scheduleJob(job, trigger);
		
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		
		job = JobBuilder.newJob(org.quartz.examples.example2.SimpleJob.class).withIdentity("job2", "group1").build();
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime).build();
		ft = sched.scheduleJob(job, trigger);
		
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		
		job = JobBuilder.newJob(org.quartz.examples.example2.SimpleJob.class).withIdentity("job3", "group1").build();
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10)).build();
		ft = sched.scheduleJob(job, trigger);
		
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger3", "group2").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(2)).forJob(job).build();
		ft = sched.scheduleJob(trigger);
		
		log.info((new StringBuilder()).append(job.getKey()).append(" will [also] run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		job = JobBuilder.newJob(org.quartz.examples.example2.SimpleJob.class).withIdentity("job4", "group1").build();
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(5)).build();
		ft = sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		job = JobBuilder.newJob(org.quartz.examples.example2.SimpleJob.class).withIdentity("job5", "group1").build();
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger5", "group1").startAt(DateBuilder.futureDate(5, org.quartz.DateBuilder.IntervalUnit.MINUTE)).build();
		ft = sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		job = JobBuilder.newJob(org.quartz.examples.example2.SimpleJob.class).withIdentity("job6", "group1").build();
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger6", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
		ft = sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		log.info("------- Starting Scheduler ----------------");
		sched.start();
		log.info("------- Started Scheduler -----------------");
		job = JobBuilder.newJob(org.quartz.examples.example2.SimpleJob.class).withIdentity("job7", "group1").build();
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();
		ft = sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(ft).append(" and repeat: ").append(trigger.getRepeatCount()).append(" times, every ")
				.append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
		job = JobBuilder.newJob(org.quartz.examples.example2.SimpleJob.class).withIdentity("job8", "group1").storeDurably().build();
		sched.addJob(job, true);
		log.info("'Manually' triggering job8...");
		sched.triggerJob(JobKey.jobKey("job8", "group1"));
		log.info("------- Waiting 3 seconds... --------------");
		try {
			Thread.sleep(10000L);
		} catch (Exception e) {
		}
		log.info("------- Rescheduling... --------------------");
		trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();
		ft = sched.rescheduleJob(trigger.getKey(), trigger);
		log.info((new StringBuilder()).append("job7 rescheduled to run at: ").append(ft).toString());
		log.info("------- Waiting 3 seconds... ------------");
		try {
			Thread.sleep(10000L);
		} catch (Exception e) {
		}
		log.info("------- Shutting Down ---------------------");
		sched.shutdown(true);
		log.info("------- Shutdown Complete -----------------");
		SchedulerMetaData metaData = sched.getMetaData();
		log.info((new StringBuilder()).append("Executed ").append(metaData.getNumberOfJobsExecuted()).append(" jobs.").toString());
	}

	public static void main(String args[]) throws Exception {
		SimpleTriggerExample example = new SimpleTriggerExample();
		example.run();
	}
}
