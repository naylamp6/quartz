package org.quartz.examples.example5;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 17, 2014 : 3:24:47 PM
 */
public class StatefulDumbJob implements  Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.err.println((new StringBuilder()).append("---").append(context.getJobDetail().getKey()).append(" executing.[").append(new Date()).append("]").toString());
		JobDataMap map = context.getJobDetail().getJobDataMap();
		int executeCount = 0;
		if (map.containsKey("NumExecutions"))
			executeCount = map.getInt("NumExecutions");
		executeCount++;
		map.put("NumExecutions", executeCount);
		long delay = 5000L;
		if (map.containsKey("ExecutionDelay"))
			delay = map.getLong("ExecutionDelay");
		try {
			Thread.sleep(delay);
		} catch (Exception ignore) {
		}
		System.err.println((new StringBuilder()).append("  -").append(context.getJobDetail().getKey()).append(" complete (").append(executeCount).append(").").toString());
	}

	public static final String NUM_EXECUTIONS = "NumExecutions";
	public static final String EXECUTION_DELAY = "ExecutionDelay";
}
