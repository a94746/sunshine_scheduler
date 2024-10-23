package com.vindie.sunshine_scheduler.util;

import com.vindie.sunshine_scheduler.config.KafkaTopic;
import com.vindie.sunshine_scheduler.dto.SchProgress;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaSender {

    private KafkaTemplate<String, SchProgress> kafkaTemplate;

    public void sendSchProgress(SchProgress schProgress) {
        kafkaTemplate.send(KafkaTopic.SCH_PROGRESS_TOPIC_NAME, schProgress);
    }
}
