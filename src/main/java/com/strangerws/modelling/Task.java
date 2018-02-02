package com.strangerws.modelling;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {
    public static final double N = 200;
    public static final double t0 = 0;
    public static final double h = 0.01;
    public static final double k = 0.01;
    public static final double nFinal = N * 0.95;
    public static final double tFinal = N * h - h + t0;

    public static double func(double x) {
        return k * x * (N - x);
    }


    public static Pair<List<Double>, List<Double>> task1() {
        System.out.print("Enter a: ");
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        List<Double> xArr = new ArrayList<Double>();
        List<Double> yArr = new ArrayList<Double>();
        double funcPrev = a;
        double funcFinal = 0;
        double tAnswer = 0;

        for (double i = 0; i <= tFinal; i += h) {
            xArr.add(i);
            yArr.add(funcPrev);
            if (funcPrev >= nFinal && funcFinal == 0) {
                funcFinal = funcPrev;
                tAnswer = i;
            }
            funcPrev += h * func(funcPrev);
        }


        System.out.println("f(0.95 * N) = " + funcFinal + "; \nt = " + tAnswer);

        return new Pair<List<Double>, List<Double>>(yArr, xArr);
    }
}
