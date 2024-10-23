package com.vindie.sunshine_scheduler.service.cache;

import com.vindie.sunshine_scheduler.dto.SchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    private final Map<String, Cachable<SchResult>> resultStore = new ConcurrentHashMap<>();

    @Override
    public SchResult getResultAndDelete(String uuid) {
        return Optional.ofNullable(resultStore.remove(uuid))
                .map(Cachable::getData)
                .orElseThrow(() -> new RuntimeException("Not found uuid=" + uuid));
    }

    @Override
    public void addResult(String uuid, SchResult result) {
        resultStore.put(uuid, new Cachable<>(result));
    }

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.MINUTES)
    public void timer() {
        var now = LocalDateTime.now();
        resultStore.entrySet().stream()
                .filter(e -> e.getValue().getCreated().plusHours(1).isBefore(now))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet())
                .forEach(uuid -> {
                    log.warn("Deleted uuid = {}", uuid);
                    resultStore.remove(uuid);
                });
    }
}
