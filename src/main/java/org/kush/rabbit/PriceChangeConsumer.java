package org.kush.rabbit;
import org.quartz.*;
import org.quartz.impl.JobExecutionContextImpl;
import org.quartz.impl.RemoteScheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceChangeConsumer implements MessageListener {
   @Override
    public void onMessage(org.springframework.amqp.core.Message message) {
       System.out.println("****"+message.getBody());
    }
}