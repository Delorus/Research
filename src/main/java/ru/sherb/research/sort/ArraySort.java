package ru.sherb.research.sort;

import java.util.Arrays;

/**
 * Created by sherb on 25.01.2017.
 */
public final class ArraySort {

    /**
     * Алгоритм классической пузырьковой сортировки, является чистой функцией
     *
     * @param array массив для сортировки
     * @return отсортированный массив
     */
    public static int[] bubbleSort(final int[] array) {
        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);

        for (int i = 1; i < result.length; i++) {
            for (int j = 0; j < result.length - i; j++) {
                if (result[j] > result[j + 1]) {
                    int tmp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = tmp;
                }
            }
        }

        return result;
    }

    /**
     * Алгоритм классической сортировки выборкой, является чистой функцией
     *
     * @param array массив для сортировки
     * @return отсортированный массив
     */
    public static int[] selectSort(final int[] array) {
        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);

        for (int i = 0; i < result.length; i++) {
            int minIndx = i;
            for (int j = i + 1; j < result.length; j++) {
                if (result[minIndx] > result[j]) {
                    minIndx = j;
                }
            }

            int tmp = result[i];
            result[i] = result[minIndx];
            result[minIndx] = tmp;
        }


        return result;
    }

    /**
     * Алгоритм классической сортировки вставками, является чистой функцией
     *
     * @param array массив для сортировки
     * @return отсортированный массив
     */
    public static int[] insertSort(final int[] array) {
        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);

        for (int currentInd = 1; currentInd < result.length; currentInd++) {
            int key = result[currentInd];
            int prevInd = currentInd - 1;
            while (prevInd >= 0 && result[prevInd] > key) {
                result[prevInd + 1] = result[prevInd];
                prevInd--;
            }
            result[prevInd + 1] = key;
        }

        return result;
    }

    /**
     * Алгоритм классической сортировки слиянием, является чистой функцией
     *
     * @param array массив для сортировки
     * @return отсортированный массив
     */
    public static int[] mergeSort(final int[] array) {
        if (array.length == 1) return array;

        int[] first = mergeSort(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] second = mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length));

        int[] result = new int[first.length + second.length];
        for (int sumInd = 0, firstInd = 0, secondInd = 0; sumInd < result.length; sumInd++) {
            // Если первый массив кончился
            if (firstInd == first.length) {
                System.arraycopy(second, secondInd, result, sumInd, second.length - secondInd);
                break;
            }

            // Если второй массив кончился
            if (secondInd == second.length) {
                System.arraycopy(first, firstInd, result, sumInd, first.length - firstInd);
                break;
            }

            result[sumInd] = first[firstInd] < second[secondInd] ? first[firstInd++] : second[secondInd++];
        }

        return result;
    }

    /**
     * Алгоритм классической сортировки Шелла, является чистой функцией
     *
     * @param array массив для сортировки
     * @return отсортированный массив
     */
    public static int[] shellSort(final int[] array) {
        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);

        int h = 1;
        // Вычисление приращения методом Кнута
        while (h <= result.length) {
            h = 3 * h + 1;
        }
        h = (h - 1) / 3;

        while (h > 0) {

            for (int i = h; i < result.length; i++) {
                int key = result[i];
                int prevInd = i - h;
                while (prevInd >= 0 && result[prevInd] > key) {
                    result[prevInd + h] = result[prevInd]; // Сдвиг элемента на одну позицию правее
                    prevInd -= h; // уменьшение индекса на одну позицию левее
                }
                result[prevInd + h] = key; // Вставка значения на свою позицию
            }
            h = (h - 1) / 3;

        }

        return result;
    }

    /**
     * Классическая сортировка подсчетом только для числовых массивов, либо массивов, элементы которых можно представить в виде перечисления.
     * <br/>
     * Асимптотическая сложность - {@code O(n + k)}, где {@code k} - диапазон возможных значений, а {@code n} - размер массива.
     * <br/>
     * Лучший вариант для случаев, когда {@code k < n} (другими словами, когда в массиве возможны повторяющиеся значения и {@code max <= n}).
     * <br/>
     * Работает только для массивов, где {@code min >= 0};
     *
     * @param array массив для сортировки
     * @return отсортированный массив
     */
    public static int[] countingSort(final int[] array) {
        int[] result = array.clone();

        // Нахождение min и max
        int min = 0, max = 0;
        for (int i = 1; i < result.length; i++) {
            if (result[max] < result[i]) {
                max = i;
            }
            if (result[min] > result[i]) {
                min = i;
            }
        }
        max = result[max];
        min = result[min];

        // Заполнение вспомогательного массива
        int[] c = new int[max];
        for (int i = 0; i < result.length; i++) {
            c[result[i] - min] += 1;
        }

        // Сортировка
        int count = 0;
        for (int i = 0; i <= max - min; i++) {
            for (int j = 0; j < c[i]; j++) {
                result[count++] = i + min;
            }
        }

        return result;
    }

}
