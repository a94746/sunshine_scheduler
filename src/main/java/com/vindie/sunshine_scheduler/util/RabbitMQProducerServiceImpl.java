package com.vindie.sunshine_scheduler.util;

import com.vindie.sunshine_scheduler_dto.SchProgress;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {

    private static final String SCH_PROGRESS_EXCHANGER = "schProgressExchange";
    private static final String SCH_PROGRESS_ROUTING_KEY = "schProgressRoutingKey";

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(SchProgress schProgress) {
        rabbitTemplate.convertAndSend(SCH_PROGRESS_EXCHANGER, SCH_PROGRESS_ROUTING_KEY, schProgress);
    }

}
