package com.vindie.sunshine_scheduler.service;

import com.vindie.sunshine_scheduler_dto.SchRequest;

public interface SchService {

    void startCalculation(SchRequest schRequest, String uuid);
}
