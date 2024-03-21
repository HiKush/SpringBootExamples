package org.kush.rabbit;

import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleJobService {
    @Autowired
    PriceChangeConsumer priceChangeConsumer;
    public void executeSampleJob(JobDataMap object){

    }
}
