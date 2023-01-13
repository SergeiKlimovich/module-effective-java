package com.javaprogram.moduleeffectivejava.cacheservice.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.javaprogram.moduleeffectivejava.cacheservice.CacheObject;
import com.javaprogram.moduleeffectivejava.cacheservice.service.CacheService;
import com.javaprogram.moduleeffectivejava.cacheservice.util.Node;
import com.javaprogram.moduleeffectivejava.cacheservice.util.NodeOperationContainer;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
public class LfuCacheServiceImpl implements CacheService {

    private static final Integer INITIAL_VALUE_FREQUENCY = 1;

    private final Map<Integer, Node> keys = new ConcurrentHashMap<>();
    private final Map<Integer, NodeOperationContainer> frequency = new ConcurrentHashMap<>();
    private final int capacity;

    @Override
    public CacheObject get(int key) {
        if (!keys.containsKey(key)) {
            return null;
        }
        return getNodeByKey(key).getCacheObject();
    }

    @Override
    public void put(int key, CacheObject cacheObject) {
        if (capacity == 0) {
            return;
        }
        if (keys.containsKey(key)) {
            Node node = getNodeByKey(key);
            node.setCacheObject(cacheObject);
        } else {
            if (keys.size() == capacity) {
                Integer leastFrequent = defineLeastFrequencyValue();
                NodeOperationContainer leastFrequentList = frequency.get(leastFrequent);
                Node node = leastFrequentList.deleteFirstNode();
                freeByNode(node);
            }
            Node node = new Node(key, cacheObject);
            addFrequencyIfAbsent(INITIAL_VALUE_FREQUENCY);
            addNodeToFrequency(node, INITIAL_VALUE_FREQUENCY);

            keys.put(key, node);
        }
    }

    private Node getNodeByKey(Integer key) {
        if (!keys.containsKey(key)) {
            return null;
        }

        Node currentNode = keys.get(key);

        NodeOperationContainer list = frequency.get(currentNode.getFrequency());
        list.deleteNodeByKey(currentNode.getKey());

        currentNode.incrementFrequency();
        addFrequencyIfAbsent(currentNode.getFrequency());
        addNodeToFrequency(currentNode, currentNode.getFrequency());

        return currentNode;
    }

    private void addFrequencyIfAbsent(Integer frequencyValue) {
        if (!frequency.containsKey(frequencyValue)) {
            frequency.put(frequencyValue, new NodeOperationContainer());
        }
    }

    private void addNodeToFrequency(Node node, Integer frequencyValue) {
        frequency.get(frequencyValue).addNode(node);
    }

    private Integer defineLeastFrequencyValue() {
        int leastFrequent = Integer.MAX_VALUE;
        for (Integer frequent : frequency.keySet()) {
            if (frequency.get(frequent).isEmpty()) {
                continue;
            }
            leastFrequent = Math.min(frequent, leastFrequent);
        }

        return leastFrequent;
    }

    private void freeByNode(Node node) {
        Integer key = node.getKey();
        LOG.info(String.format("Was removed key - %s, with value - %s",
                key,
                node.getCacheObject()));
        keys.remove(key);
    }

}
