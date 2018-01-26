package com.strangerws.modelling;

import java.util.Scanner;

public class Task {
    public static final double N = 200;
    public static final double t0 = 0;
    public static final double h = 0.01;
    public static final double k = 0.01;
    public static final double xFinal = N * 0.95;
    public static final double tN = N/h - h + t0;

    public static double func(double x) {
        return k * x * (N - x);
    }
    public static void task1(){
        System.out.print("Enter a: ");
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double xPrev;
        xPrev = a;
        for (int i = 0; i < N; i++) {
            System.out.println(xPrev);
            xPrev += h*func(xPrev);
        }
    }
}
