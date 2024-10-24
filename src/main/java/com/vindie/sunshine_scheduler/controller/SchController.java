package com.vindie.sunshine_scheduler.controller;

import com.vindie.sunshine_scheduler.service.SchService;
import com.vindie.sunshine_scheduler.service.cache.CacheService;
import com.vindie.sunshine_scheduler.util.RabbitMQProducerService;
import com.vindie.sunshine_scheduler_dto.SchRequest;
import com.vindie.sunshine_scheduler_dto.SchResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/scheduler")
public class SchController {
    private final CacheService cacheService;
    private final SchService schService;
    private final RabbitMQProducerService r;

    @PostMapping("/start_calculation")
    public String startCalculation(@RequestBody SchRequest request) {
        String uuid = UUID.randomUUID().toString();
        schService.startCalculation(request, uuid);
        return uuid;
    }

    @GetMapping("/get_calculation")
    public SchResult getCalculation(@RequestParam("uuid") String uuid) {
        return cacheService.getResultAndDelete(uuid);
    }
}
