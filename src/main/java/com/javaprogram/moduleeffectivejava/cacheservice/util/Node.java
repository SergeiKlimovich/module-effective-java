package com.javaprogram.moduleeffectivejava.cacheservice.util;

import com.javaprogram.moduleeffectivejava.cacheservice.CacheObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Node {
    @Getter
    int key;
    @Getter
    @Setter
    CacheObject cacheObject;
    @Getter
    int frequency;
    Node prev;
    Node next;

    public Node(int key, CacheObject cacheObject) {
        this.key = key;
        this.cacheObject = cacheObject;
        this.frequency = 1;
    }

    public void incrementFrequency() {
        this.frequency++;
    }
}
