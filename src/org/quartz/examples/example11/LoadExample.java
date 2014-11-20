package org.quartz.examples.example11;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 20, 2014 : 10:20:56 PM
 */
public class LoadExample {

	public LoadExample(int inNumberOfJobs) {
		_numberOfJobs = 500;
		_numberOfJobs = inNumberOfJobs;
	}

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(org.quartz.examples.example11.LoadExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete -----------");
		for (int count = 1; count <= _numberOfJobs; count++) {
			JobDetail job = JobBuilder.newJob(org.quartz.examples.example11.SimpleJob.class).withIdentity((new StringBuilder()).append("job").append(count).toString(), "group_1").requestRecovery()
					.build();
			long timeDelay = (long) (Math.random() * 2500D);
			job.getJobDataMap().put("delay time", timeDelay);
			org.quartz.Trigger trigger = TriggerBuilder.newTrigger().withIdentity((new StringBuilder()).append("trigger_").append(count).toString(), "group_1")
					.startAt(DateBuilder.futureDate(10000 + count * 100, org.quartz.DateBuilder.IntervalUnit.MILLISECOND)).build();
			sched.scheduleJob(job, trigger);
			if (count % 25 == 0)
				log.info((new StringBuilder()).append("...scheduled ").append(count).append(" jobs").toString());
		}

		log.info("------- Starting Scheduler ----------------");
		sched.start();
		log.info("------- Started Scheduler -----------------");
		log.info("------- Waiting five minutes... -----------");
		try {
			Thread.sleep(300000L);
		} catch (Exception e) {
		}
		log.info("------- Shutting Down ---------------------");
		sched.shutdown(true);
		log.info("------- Shutdown Complete -----------------");
		SchedulerMetaData metaData = sched.getMetaData();
		log.info((new StringBuilder()).append("Executed ").append(metaData.getNumberOfJobsExecuted()).append(" jobs.").toString());
	}

	public static void main(String args[]) throws Exception {
		int numberOfJobs = 500;
		if (args.length == 1)
			numberOfJobs = Integer.parseInt(args[0]);
		if (args.length > 1) {
			System.out.println((new StringBuilder()).append("Usage: java ").append(org.quartz.examples.example11.LoadExample.class.getName()).append("[# of jobs]").toString());
			return;
		} else {
			LoadExample example = new LoadExample(numberOfJobs);
			example.run();
			return;
		}
	}

	private int _numberOfJobs;
}
