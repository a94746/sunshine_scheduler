package com.vindie.sunshine_scheduler.service;

import com.vindie.sunshine_scheduler.dto.SchRequest;
import com.vindie.sunshine_scheduler.dto.SchResult;

public interface SchService {

    void startCalculation(SchRequest schRequest, String uuid);
}
