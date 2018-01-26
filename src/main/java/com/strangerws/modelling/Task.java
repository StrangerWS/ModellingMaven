package com.strangerws.modelling;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {
    public static final double N = 1000;
    public static final double t0 = 0;
    public static final double h = 0.01;
    public static final double k = 0.01;
    public static final double nFinal = N * 0.95;

    public static double func(double x) {
        return k * x * (N - x);
    }
    public static Pair<List<Double>, List<Double>> task1(){
        System.out.print("Enter a: ");
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        List<Double> xArr = new ArrayList<Double>();
        List<Double> yArr = new ArrayList<Double>();
        double tPrev = a;
        double tFinal = 0;
        for (int i = 0; i <= N; i++) {
            xArr.add(tPrev);
            yArr.add((double)i);
            tPrev += h*func(tPrev);
            if (i == nFinal) {tFinal = tPrev;}
        }

        System.out.println("x: 0.95 * N; \nt = "+tFinal);

        return new Pair<List<Double>, List<Double>>(xArr, yArr);
    }
}
