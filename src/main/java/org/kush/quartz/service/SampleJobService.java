package org.kush.quartz.service;

import org.kush.quartz.entity.PriceChangeEntity;
import org.kush.quartz.repository.SampleDatabaseRepository;
import org.kush.quartz.vo.PriceChange;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SampleJobService {
    @Autowired
    SampleDatabaseRepository sampleDatabaseRepository;

  //  @Retryable(value = DataAccessException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public void executeSampleJob(JobDataMap jobDataMap) {
        String jobName = "testName";
        List<PriceChange> priceChanges = (List<PriceChange>) jobDataMap.get(jobName);
        List<PriceChangeEntity> priceChangeEntities = priceChanges.stream().map(p -> {
            PriceChangeEntity pe = new PriceChangeEntity();
            pe.setPriceChange(p.getPriceChange());
            return pe;
        }).collect(Collectors.toList());
        priceChangeEntities.stream().forEach(p ->{sampleDatabaseRepository.save(p);
            System.out.println("saved");});

    }
}
