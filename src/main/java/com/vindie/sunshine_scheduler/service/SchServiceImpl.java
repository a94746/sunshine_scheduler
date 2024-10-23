package com.vindie.sunshine_scheduler.service;

import com.vindie.sunshine_scheduler.dto.*;
import com.vindie.sunshine_scheduler.service.cache.CacheService;
import com.vindie.sunshine_scheduler.service.engine.Engine;
import com.vindie.sunshine_scheduler.service.engine.SmalRules;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SchServiceImpl implements SchService {

    private final CacheService cacheService;

    @Override
    @Async
    public void startCalculation(SchRequest schRequest, String uuid) {
        calculate(schRequest);

        SchResult result = new SchResult();
        result.setMatches(getMatches(schRequest.getAccounts()));
        result.setMetrics(getMetrics(schRequest.getAccounts()));
        cacheService.addResult(uuid, result);
    }

    private void calculate(SchRequest schRequest) {
        var engine = new Engine(schRequest.getProperties());

        for (SchAccount theAccount : schRequest.getAccounts()) {
            for (SchAccount acc : schRequest.getAccounts()) {
                if (engine.main.test(theAccount, acc)) {
                    addMatchEachOther(theAccount, acc);
                    if (SmalRules.ONE_ENOUGH_MATCHES.test(theAccount))
                        break;
                }
            }
        }
    }

    private void addMatchEachOther(SchAccount acc1, SchAccount acc2) {
        acc1.getResultPartners().add(acc2.getId());
        acc2.getResultPartners().add(acc1.getId());
    }

    private SchMetrics getMetrics(Collection<SchAccount> accounts) {
        double avgMatches = accounts.stream()
                .map(SchAccount::getResultPartners)
                .mapToInt(Set::size)
                .average()
                .orElse(0);

        SchMetrics metrics = new SchMetrics();
        metrics.setAvgMatches(avgMatches);
        return metrics;
    }

    private Collection<SchMatch> getMatches(Collection<SchAccount> accounts) {
        return accounts.stream()
                .map(this::getMatchesForAccount)
                .flatMap(Collection::stream)
                .toList();
    }

    private Set<SchMatch> getMatchesForAccount(SchAccount account) {
        long ownerId = account.getId();
        return account.getResultPartners()
                .stream()
                .map(partnerId -> new SchMatch(ownerId, partnerId))
                .collect(Collectors.toSet());
    }
}
