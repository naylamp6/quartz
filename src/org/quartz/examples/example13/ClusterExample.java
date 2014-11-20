package org.quartz.examples.example13;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
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
 * @time Nov 20, 2014 : 10:26:15 PM
 */
public class ClusterExample {

	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example13.ClusterExample.class);
	
	public void run(boolean inClearJobs, boolean inScheduleJobs) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		if (inClearJobs) {
			_log.warn("***** Deleting existing jobs/triggers *****");
			sched.clear();
		}
		_log.info("------- Initialization Complete -----------");
		if (inScheduleJobs) {
			_log.info("------- Scheduling Jobs ------------------");
			String schedId = sched.getSchedulerInstanceId();
			int count = 1;
			JobDetail job = JobBuilder.newJob(org.quartz.examples.example13.SimpleRecoveryJob.class).withIdentity((new StringBuilder()).append("job_").append(count).toString(), schedId)
					.requestRecovery().build();
			SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity((new StringBuilder()).append("triger_").append(count).toString(), schedId)
					.startAt(DateBuilder.futureDate(1, org.quartz.DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5))
					.build();
			_log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(trigger.getNextFireTime()).append(" and repeat: ").append(trigger.getRepeatCount())
					.append(" times, every ").append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
			sched.scheduleJob(job, trigger);
			count++;
			job = JobBuilder.newJob(org.quartz.examples.example13.SimpleRecoveryJob.class).withIdentity((new StringBuilder()).append("job_").append(count).toString(), schedId).requestRecovery()
					.build();
			trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity((new StringBuilder()).append("triger_").append(count).toString(), schedId)
					.startAt(DateBuilder.futureDate(2, org.quartz.DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5))
					.build();
			_log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(trigger.getNextFireTime()).append(" and repeat: ").append(trigger.getRepeatCount())
					.append(" times, every ").append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
			sched.scheduleJob(job, trigger);
			count++;
			job = JobBuilder.newJob(org.quartz.examples.example13.SimpleRecoveryStatefulJob.class).withIdentity((new StringBuilder()).append("job_").append(count).toString(), schedId)
					.requestRecovery().build();
			trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity((new StringBuilder()).append("triger_").append(count).toString(), schedId)
					.startAt(DateBuilder.futureDate(1, org.quartz.DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(3))
					.build();
			_log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(trigger.getNextFireTime()).append(" and repeat: ").append(trigger.getRepeatCount())
					.append(" times, every ").append(trigger.getRepeatInterval() / 1000L).append(" seconds").toString());
			sched.scheduleJob(job, trigger);
			count++;
			job = JobBuilder.newJob(org.quartz.examples.example13.SimpleRecoveryJob.class).withIdentity((new StringBuilder()).append("job_").append(count).toString(), schedId).requestRecovery()
					.build();
			trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity((new StringBuilder()).append("triger_").append(count).toString(), schedId)
					.startAt(DateBuilder.futureDate(1, org.quartz.DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(4))
					.build();
			_log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(trigger.getNextFireTime()).append(" & repeat: ").append(trigger.getRepeatCount()).append("/")
					.append(trigger.getRepeatInterval()).toString());
			sched.scheduleJob(job, trigger);
			count++;
			job = JobBuilder.newJob(org.quartz.examples.example13.SimpleRecoveryJob.class).withIdentity((new StringBuilder()).append("job_").append(count).toString(), schedId).requestRecovery()
					.build();
			trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity((new StringBuilder()).append("triger_").append(count).toString(), schedId)
					.startAt(DateBuilder.futureDate(1, org.quartz.DateBuilder.IntervalUnit.SECOND))
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInMilliseconds(4500L)).build();
			_log.info((new StringBuilder()).append(job.getKey()).append(" will run at: ").append(trigger.getNextFireTime()).append(" & repeat: ").append(trigger.getRepeatCount()).append("/")
					.append(trigger.getRepeatInterval()).toString());
			sched.scheduleJob(job, trigger);
		}
		_log.info("------- Starting Scheduler ---------------");
		sched.start();
		_log.info("------- Started Scheduler ----------------");
		_log.info("------- Waiting for one hour... ----------");
		try {
			Thread.sleep(3600000L);
		} catch (Exception e) {
		}
		_log.info("------- Shutting Down --------------------");
		sched.shutdown();
		_log.info("------- Shutdown Complete ----------------");
	}

	public static void main(String args[]) throws Exception {
		boolean clearJobs = false;
		boolean scheduleJobs = true;
		String arr$[] = args;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			String arg = arr$[i$];
			if (arg.equalsIgnoreCase("clearJobs")) {
				clearJobs = true;
				continue;
			}
			if (arg.equalsIgnoreCase("dontScheduleJobs"))
				scheduleJobs = false;
		}

		ClusterExample example = new ClusterExample();
		example.run(clearJobs, scheduleJobs);
	}

}
