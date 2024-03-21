package org.kush.quartz.service;

import org.kush.quartz.vo.PriceChange;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceChangeService {
    @Autowired
    @Qualifier("specialBean")
    private Scheduler scheduler;

    public String triggerJob(String jobName, String jobGroup,List<PriceChange> lstPriceChange) throws SchedulerException {
      JobDataMap jobDataMap= scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup)).getJobDataMap();
        jobDataMap.put(jobName,lstPriceChange);
        scheduler.triggerJob(JobKey.jobKey(jobName, jobGroup),jobDataMap);
        return "success";
    }
}

