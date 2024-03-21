package org.kush.rabbit;

import org.kush.quartz.vo.PriceChange;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceChangeProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;
    public void sendMessage(List<PriceChange> message) {
        amqpTemplate.convertAndSend("myQueue", message); // Replace "myQueue" with your queue name
    }
}