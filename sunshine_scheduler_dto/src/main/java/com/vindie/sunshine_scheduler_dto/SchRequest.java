package com.vindie.sunshine_scheduler_dto;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SchRequest {
    private Collection<SchAccount> accounts;
    private SchProperties properties;
}
