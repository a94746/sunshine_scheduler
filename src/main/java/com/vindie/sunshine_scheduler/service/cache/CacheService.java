package com.vindie.sunshine_scheduler.service.cache;

import com.vindie.sunshine_scheduler.dto.SchResult;

public interface CacheService {

    SchResult getResultAndDelete(String uuid);

    void addResult(String uuid, SchResult result);
}
