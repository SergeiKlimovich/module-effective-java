package com.javaprogram.moduleeffectivejava.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeExecutor {

    public List<Integer> traverse(Tree tree) {
        List<Integer> result = new ArrayList<>();
        Tree rightTree = tree.getRight();
        Tree leftTree = tree.getLeft();

        if (Objects.nonNull(rightTree)) {
            result.addAll(traverse(rightTree));
        }
        if (Objects.nonNull(leftTree)) {
            result.addAll(traverse(leftTree));
        }

        result.add(tree.getValue());
        return result;
    }
}
