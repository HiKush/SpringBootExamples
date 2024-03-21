package org.kush.quartz.repository;

import org.kush.quartz.entity.PriceChangeEntity;
import org.kush.quartz.vo.PriceChange;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDatabaseRepository extends MongoRepository<PriceChangeEntity,Long> {

    @Retryable(value = DataAccessException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    PriceChangeEntity save(PriceChangeEntity entity);
}
