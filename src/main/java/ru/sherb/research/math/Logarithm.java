package ru.sherb.research.math;

final public class Logarithm {

    private Logarithm() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Нахождение логарифма по основанию 2, алгоритм работает оптимально на JIT-client,
     * на server проводятся агрессивные оптимизации над стандартной библиотекой
     * и уже нет существенного прироста в скорости
     * <p/>
     * log2(bits)
     * <p/>
     * https://stackoverflow.com/questions/3305059/how-do-you-calculate-log-base-2-in-java-for-integers
     */
    public static int binlog(int bits) {
        int log = 0;
        if ((bits & 0xffff0000) != 0) {
            bits >>>= 16;
            log = 16;
        }
        if (bits >= 256) {
            bits >>>= 8;
            log += 8;
        }
        if (bits >= 16) {
            bits >>>= 4;
            log += 4;
        }
        if (bits >= 4) {
            bits >>>= 2;
            log += 2;
        }
        return log + (bits >>> 1);
    }

    /**
     * Нахождение логарифма по основанию 2 с помощью стандартной библиотеки, на JIT-server показывает лучшие результаты,
     * чем самописный вариант из-за агрессивной оптимизации
     * https://stackoverflow.com/questions/3305059/how-do-you-calculate-log-base-2-in-java-for-integers
     */
    public static int log2(int bits) {
        if (bits == 0) {
            return 0;
        }
        return 31 - Integer.numberOfLeadingZeros(bits);
    }
}
