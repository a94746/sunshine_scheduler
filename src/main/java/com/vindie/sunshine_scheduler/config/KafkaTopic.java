package com.vindie.sunshine_scheduler.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    public static final String SCH_PROGRESS_TOPIC_NAME = "sch-progress";

    @Bean
    public NewTopic topicSchProgress() {
        return TopicBuilder.name(SCH_PROGRESS_TOPIC_NAME).build();
    }
}
