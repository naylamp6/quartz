package com.quartz.test;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * 
 * @author : yangjianzhou
 * @description : TODO
 * @time : Nov 21, 2014,9:02:30 PM
 */
public class Test {

	public static void main(String[] args) throws Exception {
		SchedulerFactory scheduleFactory = new StdSchedulerFactory();
		Scheduler scheduler = scheduleFactory.getScheduler();
		
		scheduler.start();
		
		TestJobListener jobListener = new TestJobListener();
		scheduler.getListenerManager().addJobListener(jobListener,KeyMatcher.keyEquals(new JobKey("job1","group1")));
		JobDetail  jobDetail = JobBuilder.newJob(com.quartz.test.TestJob.class).withIdentity("job1", "group1").usingJobData("count", 0).build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow().
				withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
		scheduler.scheduleJob(jobDetail, trigger);
		int count = jobDetail.getJobDataMap().getInt("count");
		count++;
		jobDetail.getJobDataMap().put("count", count);
		try {
			Thread.sleep(10000L);
		} catch (Exception e) {
		}
		scheduler.shutdown(true);
	}

}
