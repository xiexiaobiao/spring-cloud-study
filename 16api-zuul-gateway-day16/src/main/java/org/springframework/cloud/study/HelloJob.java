package org.springframework.cloud.study;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @Classname HelloJob
 * @Description TODO
 * @Author xiexiaobiao
 * @Date 2019-05-28 18:25
 * @Version 1.0
 **/
public class HelloJob implements Job {

    JobImpl job = new JobImpl();

    public void execute(JobExecutionContext jobExecutionContext) {
        job.pringStr();

    }
}
