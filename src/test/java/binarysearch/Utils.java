package binarysearch;

import java.util.Arrays;

import lombok.extern.java.Log;

@Log
public class Utils {

    public static void measureTime(Runnable task, String taskName) {
         long before = System.currentTimeMillis();
        LOG.info("Task " + taskName + " started at " + before);
        task.run();
        long after = System.currentTimeMillis();
        LOG.info("Task " + taskName + " finished at " + after);
        LOG.info("Task time in " + taskName +  " is " + (after - before));
    }

    public static int[] generateRandomArrayWithLengthAndAppendGuaranteedElement(int length, int guaranteedElement,
                                                                                boolean isSorted) {
        int[] array = new int[length];

        for (int i = 0; i < length - 1; i++) {
            array[i] = (int) Math.round(Math.random() * 10000);
        }

        array[length - 1] = guaranteedElement;

        if (isSorted) {
            Arrays.sort(array);
        }

        return array;
    }
}
