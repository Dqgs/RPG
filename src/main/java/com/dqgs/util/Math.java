package com.dqgs.util;

public class Math {
    public static double Round(double number){
        double d = number;
        double dr = (int) ((d * 100) + 0.5);
        double finalValue = dr / 100;

    return finalValue;
    }
}
