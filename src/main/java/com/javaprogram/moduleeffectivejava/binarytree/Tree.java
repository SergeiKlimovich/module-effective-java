package com.javaprogram.moduleeffectivejava.binarytree;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tree {

    private int value;
    private Tree right;
    private Tree left;

    public Tree(int value) {
        this.value = value;
    }
}
