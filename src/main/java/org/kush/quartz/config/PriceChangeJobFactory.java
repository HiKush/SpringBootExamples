package org.kush.quartz.config;

import org.kush.quartz.service.PriceChangeService;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class PriceChangeJobFactory extends SpringBeanJobFactory {

    @Autowired
    private PriceChangeService priceChangeService;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object job = super.createJobInstance(bundle);
        return job;
    }
}
