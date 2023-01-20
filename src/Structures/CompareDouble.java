package Structures;

public class CompareDouble {
    public static Double EPS = 1e-6;

    public static boolean less(final Double a, final Double b) {
        return (a - b) < EPS;
    }

    public static boolean equal(final Double a, final Double b) {
        return Math.abs(a - b) < EPS;
    }

    public static boolean more(final Double a, final Double b) {
        return (a - b) > EPS;
    }

    public static boolean lessOrEqual(final Double a, final Double b) {
        return less(a, b) | equal(a, b);
    }

    public static boolean moreOrEqual(final Double a, final Double b) {
        return more(a, b) | equal(a, b);
    }


}
