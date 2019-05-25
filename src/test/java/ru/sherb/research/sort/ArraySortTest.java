package ru.sherb.research.sort;

import org.junit.jupiter.api.Test;
import ru.sherb.research.sort.ArraySort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class ArraySortTest {
    private static final int N = 100_000;

    private static int[] array = new int[N];
    static {
        Random rmd = new Random();
        for (int i = 0; i < N; i++) {
            array[i] = rmd.nextInt(array.length);
        }
        System.out.println("initial array = " + Arrays.toString(array));
    }

    @Test
    void shellSort() {

        long timeStart = System.currentTimeMillis();
        int[] sort = ArraySort.shellSort(array);
        long timeEnd = System.currentTimeMillis() - timeStart;

        for (int i = 0; i < sort.length - 1; i++) {
            if (sort[i] > sort[i + 1]) {
                fail("bad sorting");
            }
        }

        System.out.println("shell sorting time = " + timeEnd + "ms.");
        System.out.println("sort array = " + Arrays.toString(sort));

    }

    @Test
    void mergeSort() {

        long timeStart = System.currentTimeMillis();
        int[] sort = ArraySort.mergeSort(array);
        long timeEnd = System.currentTimeMillis() - timeStart;

        for (int i = 0; i < sort.length - 1; i++) {
            if (sort[i] > sort[i + 1]) {
                fail("bad sorting");
            }
        }

        System.out.println("merge sorting time = " + timeEnd + "ms.");
        System.out.println("sort array = " + Arrays.toString(sort));

    }

    @Test
    void insertSort() {

        long timeStart = System.currentTimeMillis();
        int[] sort = ArraySort.insertSort(array);
        long timeEnd = System.currentTimeMillis() - timeStart;

        for (int i = 0; i < sort.length - 1; i++) {
            if (sort[i] > sort[i + 1]) {
                fail("bad sorting");
            }
        }

        System.out.println("insert sorting time = " + timeEnd + "ms.");
        System.out.println("sort array = " + Arrays.toString(sort));

    }

    @Test
    void selectSort() {

        long timeStart = System.currentTimeMillis();
        int[] sort = ArraySort.selectSort(array);
        long timeEnd = System.currentTimeMillis() - timeStart;

        for (int i = 0; i < sort.length - 1; i++) {
            if (sort[i] > sort[i + 1]) {
                fail("bad sorting");
            }
        }

        System.out.println("select sorting time = " + timeEnd + "ms.");
        System.out.println("sort array = " + Arrays.toString(sort));

    }

    @Test
    void bubbleSort() {

        long timeStart = System.currentTimeMillis();
        int[] sort = ArraySort.bubbleSort(array);
        long timeEnd = System.currentTimeMillis() - timeStart;

        for (int i = 0; i < sort.length - 1; i++) {
            if (sort[i] > sort[i + 1]) {
                fail("bad sorting");
            }
        }

        System.out.println("bubble sorting time = " + timeEnd + "ms.");
        System.out.println("sort array = " + Arrays.toString(sort));
    }




}