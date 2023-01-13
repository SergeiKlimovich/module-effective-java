package com.javaprogram.moduleeffectivejava.cacheservice.service;

import com.javaprogram.moduleeffectivejava.cacheservice.CacheObject;

public interface CacheService {
    CacheObject get(int key);

    void put(int key, CacheObject cacheObject);
}
