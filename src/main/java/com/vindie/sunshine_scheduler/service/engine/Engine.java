package com.vindie.sunshine_scheduler.service.engine;


import com.vindie.sunshine_scheduler_dto.SchAccount;
import com.vindie.sunshine_scheduler_dto.SchProperties;

import java.util.function.BiPredicate;

public class Engine {

    private final SmalRules smalRules;

    public final BiPredicate<SchAccount, SchAccount> main;

    public Engine(SchProperties properties) {
        this.smalRules = new SmalRules(properties);

        this.main = smalRules.TWO_NOT_ENOUGH_MATCHES
                .and(smalRules.NOT_THE_SAME_ACC)
                .and(smalRules.NUT_IN_AVOID_MATCHES)
                .and(smalRules.TWO_SUITABLE_GENDER)
                .and(smalRules.TWO_SUITABLE_AGE)
                .and(smalRules.TWO_SUITABLE_LAST_PRESENCE)
                .and(smalRules.TWO_SUITABLE_RAITING);
    }

}
