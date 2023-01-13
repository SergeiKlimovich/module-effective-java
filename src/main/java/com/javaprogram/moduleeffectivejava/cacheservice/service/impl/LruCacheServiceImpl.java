package com.javaprogram.moduleeffectivejava.cacheservice.service.impl;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.cache.RemovalListener;
import com.javaprogram.moduleeffectivejava.cacheservice.CacheObject;
import com.javaprogram.moduleeffectivejava.cacheservice.service.CacheService;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Log
@Log4j2
public class LruCacheServiceImpl implements CacheService {

    private final Cache<Integer, CacheObject> cache;

    public LruCacheServiceImpl(int capacity) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(capacity)
                .concurrencyLevel(8)
                .removalListener((RemovalListener<Integer, CacheObject>) removalNotification ->
                        LOG.info(String.format("Key - %s, with value - %s was removed",
                                removalNotification.getKey(), removalNotification.getValue())))
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .recordStats()
                .build();
    }

    @Override
    public CacheObject get(int key) {
        return cache.getIfPresent(key);
    }

    @Override
    public void put(int key, CacheObject cacheObject) {
        cache.put(key, cacheObject);
    }

    public void logStats() {
        CacheStats cacheStats = cache.stats();
        LOG.info(String.format(
                "Average time spent for putting new values into the cache - %s, number of cache evictions - %s",
                cacheStats.averageLoadPenalty(),
                cacheStats.evictionCount()));
    }
}
