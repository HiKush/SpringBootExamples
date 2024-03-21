package org.kush.quartz.config;

import org.kush.quartz.job.SampleJob;
import org.quartz.*;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class PriceConfiguration implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Bean
    public JobDetail job() {
        return JobBuilder.newJob().ofType(SampleJob.class)
                .storeDurably()
                .withIdentity("Qrtz_Job_Detail")
                .withDescription("Invoke Sample Job service...")
                .build();
    }
    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("Qrtz_Trigger")
                .withDescription("Sample trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInHours(1))
                .build();
    }

    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {

        return DataSourceBuilder.create()
                .url("jdbc:mongodb://localhost:27017")
                .driverClassName("mongodb.jdbc.MongoDriver")
                .username("")
                .password("")
                .build();
    }

    @Bean(name = "specialBean")
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean schedulerFactoryBean)
            throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        return scheduler;
    }
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger, JobDetail job, DataSource quartzDataSource) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        schedulerFactory.setDataSource(quartzDataSource);
        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringJobBeanFactory jobFactory = new AutoWiringJobBeanFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}