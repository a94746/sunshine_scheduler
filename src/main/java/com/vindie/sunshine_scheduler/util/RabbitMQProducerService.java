package com.vindie.sunshine_scheduler.util;

import com.vindie.sunshine_scheduler_dto.SchProgress;

public interface RabbitMQProducerService {

    void sendMessage(SchProgress schProgress);
}
