package binarysearch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import com.javaprogram.moduleeffectivejava.binarysearch.SortExecutor;

class SortExecutorTest {

    private final SortExecutor sut = new SortExecutor();

    @Test
    void shouldCompareMergeSortAndInsertionSortWhenArraySizeLarge() {
        int[] array = Utils.generateRandomArrayWithLengthAndAppendGuaranteedElement(1_000_000, 20000, false);
        Utils.measureTime(() -> sut.mergeSort(array), "Merge sort");
        Utils.measureTime(() -> sut.mergeSort(array), "Insertion sort");
    }

    @Test
    void shouldCompareMergeSortAndInsertionSortWhenArraySizeSmall() {
        int[] array = Utils.generateRandomArrayWithLengthAndAppendGuaranteedElement(30, 20000, false);
        Utils.measureTime(() -> sut.mergeSort(array), "Merge sort");
        Utils.measureTime(() -> sut.mergeSort(array), "Insertion sort");
    }

    @Test
    void shouldSortViaMergeSort() {
        int[] array = new int[] {90, 24, 3, 23, 65, 5, 200, 0, 5};
        int[] expected = new int[] {0, 3, 5, 5, 23, 24, 65, 90, 200};

        int[] actual = sut.mergeSort(array);

        assertThat(actual, is(expected));
    }

    @Test
    void shouldSortViaInsertionSort() {
        int[] array = new int[] {90, 24, 3, 23, 65, 5, 200, 0, 5};
        int[] expected = new int[] {0, 3, 5, 5, 23, 24, 65, 90, 200};

        sut.insertionSort(array);

        assertThat(array, is(expected));
    }

}
