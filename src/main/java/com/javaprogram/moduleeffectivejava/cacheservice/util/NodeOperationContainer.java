package com.javaprogram.moduleeffectivejava.cacheservice.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NodeOperationContainer {
    private final Map<Integer, Node> nodes = new ConcurrentHashMap<>();

    private final Node nodeHead;
    private final Node nodeTail;

    public NodeOperationContainer() {
        nodeHead = new Node();
        nodeTail = new Node();
        nodeHead.next = nodeTail;
        nodeTail.prev = nodeHead;
    }

    public void addNode(Node node) {
        nodeTail.prev.next = node;
        node.prev = nodeTail.prev;
        nodeTail.prev = node;
        node.next = nodeTail;
        nodes.put(node.key, node);
    }

    public Node deleteNodeByKey(int key) {
        if (!nodes.containsKey(key)) {
            return null;
        }
        Node currentNode = nodes.get(key);
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        nodes.remove(key);
        return currentNode;
    }

    public Node deleteFirstNode() {
        if (nodeHead.next == nodeTail) {
            return null;
        }
        return deleteNodeByKey(nodeHead.next.key);
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }
}



















