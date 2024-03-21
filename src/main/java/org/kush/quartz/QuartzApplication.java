package org.kush.quartz;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = {"org.kush.quartz","org.kush.quartz.config"})
@Configuration
public class QuartzApplication implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}

