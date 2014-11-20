package org.quartz.examples.example12;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 20, 2014 : 10:24:19 PM
 */
public class RemoteServerExample {

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(org.quartz.examples.example12.RemoteServerExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete -----------");
		log.info("------- (Not Scheduling any Jobs - relying on a remote client to schedule jobs --");
		log.info("------- Starting Scheduler ----------------");
		sched.start();
		log.info("------- Started Scheduler -----------------");
		log.info("------- Waiting ten minutes... ------------");
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
		RemoteServerExample example = new RemoteServerExample();
		example.run();
	}
	
}
