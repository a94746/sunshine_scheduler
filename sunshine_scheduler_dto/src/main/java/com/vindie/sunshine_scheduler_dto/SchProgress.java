package com.vindie.sunshine_scheduler_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchProgress implements Serializable {
    private String uuid;
    private Status status;

    public enum Status {
        IN_PROGRESS,
        DONE,
        ERROR
    }
}
