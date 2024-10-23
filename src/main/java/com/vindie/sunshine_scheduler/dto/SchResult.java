package com.vindie.sunshine_scheduler.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SchResult {

    private Collection<SchMatch> matches = new ArrayList<>();
    private SchMetrics metrics;
}
