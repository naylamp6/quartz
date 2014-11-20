package org.quartz.examples.example7;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangjianzhou
 * @description TODO
 * @time Nov 17, 2014 : 3:34:43 PM
 */
public class DumbInterruptableJob implements Job {

	private static Logger _log = LoggerFactory.getLogger(org.quartz.examples.example7.DumbInterruptableJob.class);
	private boolean _interrupted;
	private JobKey _jobKey;

	public DumbInterruptableJob() {
		_interrupted = false;
		_jobKey = null;
	}

	public void execute(JobExecutionContext context)
        throws JobExecutionException
    {
        _jobKey = context.getJobDetail().getKey();
        _log.info((new StringBuilder()).append("---- ").append(_jobKey).append(" executing at ").append(new Date()).toString());
        int i = 0;
     _L1:
        if(i >= 4)
            break MISSING_BLOCK_LABEL_175;
        try
        {
            Thread.sleep(1000L);
        }
        catch(Exception ignore)
        {
            ignore.printStackTrace();
        }
        if(!_interrupted)
            break MISSING_BLOCK_LABEL_169;
        _log.info((new StringBuilder()).append("--- ").append(_jobKey).append("  -- Interrupted... bailing out!").toString());
        _log.info((new StringBuilder()).append("---- ").append(_jobKey).append(" completed at ").append(new Date()).toString());
        return;
        i++;
          goto _L1 ;
        _log.info((new StringBuilder()).append("---- ").append(_jobKey).append(" completed at ").append(new Date()).toString());
        break MISSING_BLOCK_LABEL_273;
        Exception exception;
        exception;
        _log.info((new StringBuilder()).append("---- ").append(_jobKey).append(" completed at ").append(new Date()).toString());
        throw exception;
    }

	public void interrupt() throws UnableToInterruptJobException {
		_log.info((new StringBuilder()).append("---").append(_jobKey).append("  -- INTERRUPTING --").toString());
		_interrupted = true;
	}
}
