package binarytree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;
import com.javaprogram.moduleeffectivejava.binarytree.Tree;
import com.javaprogram.moduleeffectivejava.binarytree.TreeExecutor;

public class TreeExecutorTest {

    private final TreeExecutor sut = new TreeExecutor();

    @Test
    void shouldTraverseTree() {
        Tree rootTree = new Tree(7,
                new Tree(3,
                        new Tree(25),
                        new Tree(78)),
                new Tree(19));

        List<Integer> expected = ImmutableList.of(25, 78, 3, 19, 7);
        List<Integer> actual = sut.traverse(rootTree);
        assertThat(actual, is(expected));
    }
}
