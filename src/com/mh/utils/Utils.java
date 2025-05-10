package com.mh.utils;

/**
 *
 * @author vladu
 */
public class Utils {

    public static double redondeoDosDecimales(double num) {
        return 1.0d * Math.round(num * 100.0d) / 100.0d;
    }
}
