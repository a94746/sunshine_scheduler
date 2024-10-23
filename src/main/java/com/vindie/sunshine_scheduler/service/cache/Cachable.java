package com.vindie.sunshine_scheduler.service.cache;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Cachable<T> {

    private final T data;
    private LocalDateTime created = LocalDateTime.now();

    public Cachable(T data) {
        this.data = data;
    }
}
