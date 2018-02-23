package com.aliadnank.services.impl;

import com.aliadnank.services.TransactionStatsService;
import com.aliadnank.components.TransactionStore;
import com.aliadnank.domains.TransactionStats;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

import static com.aliadnank.util.Constants.LAST_MIN_DELAY_IN_SEC;

@Service("statsService")
public class TransactionStatsServiceImpl implements TransactionStatsService {

    @Autowired
    private TransactionStore transactionStore;

    @Override
    public TransactionStats reduce() {
        TransactionStats stats = new TransactionStats();
        DoubleSummaryStatistics computedStats = transactionStore.getStore().
                tailMap(Instant.now().minusSeconds(LAST_MIN_DELAY_IN_SEC).toEpochMilli()).values().
                stream().flatMap(t -> t.stream()).
                collect(Collectors.toList()).parallelStream().mapToDouble((t) -> t.getAmount()).summaryStatistics();
        BeanUtils.copyProperties(computedStats,stats);
        stats.setAvg(computedStats.getAverage());
        return stats;
    }
}
