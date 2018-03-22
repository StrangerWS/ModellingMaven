package com.strangerws.modelling;

import javafx.util.Pair;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Task {
    public static final double N = 100;
    public static final double t0 = 0;
    public static final double h = 0.01;
    public static final double k = 0.1;
    public static final double nFinal = N * 0.95;
    public static final double tFinal = 1000;

    public static double func(double x) {
        return k * x * (N - x);
    }

    public static Pair<Double, Double> system(Pair<Double, Double> coordinates, double a1, double a2) {
        double x = coordinates.getKey();
        double y = coordinates.getValue();

        double fx = x + h * (a1 * x + Math.pow(x, 2) * y + Math.pow(x, 3) * y + Math.pow(x, 4) * y);
        double gy = y + h * (a2 * y + Math.pow(y, 2) * x + Math.pow(y, 3) * x + Math.pow(y, 4) * x);

        return new Pair<Double, Double>(fx, gy);
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

        double t = t0;

        while (t < tFinal) {
            t += h;
            xArr.add(t);
            yArr.add(funcPrev);
            if (funcPrev >= nFinal && funcFinal == 0) {
                funcFinal = funcPrev;
                tAnswer = t;
            }
            funcPrev += h * func(funcPrev);
        }


        System.out.println("f(0.95 * N) = " + funcFinal + "; \nt = " + tAnswer);

        return new Pair<List<Double>, List<Double>>(xArr, yArr);
    }

    public static void task2() {
        List<List<Double>> xArr = new ArrayList<List<Double>>();
        List<List<Double>> yArr = new ArrayList<List<Double>>();

        System.out.print("Enter a1: ");
        //Scanner scanner = new Scanner(System.in);
        double a1 = -2d;//scanner.nextDouble();
        //System.out.print("Enter a2: ");
        double a2 = -8d;//scanner.nextDouble();

        double t;
        double[] x = {1, -1, -1, 1, 0.55};
        double[] y = {1, 1, -1, -1, 2};

        Pair<Double, Double> coordinates;
        List<XYChart> charts = new ArrayList<XYChart>();
        XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();

        for (int i = 0; i < x.length; i++) {
            xArr.add(i, new ArrayList<Double>());
            yArr.add(i, new ArrayList<Double>());
            t = t0;

            while (t < tFinal) {
                if (!Double.isInfinite(x[i]) && !Double.isInfinite(y[i])) {
                    xArr.get(i).add(x[i]);
                    yArr.get(i).add(y[i]);
                } else {
                    xArr.get(i).add(0d);
                    yArr.get(i).add(0d);
                }
                coordinates = system(new Pair<Double, Double>(x[i], y[i]), a1, a2);

                t += h;
                x[i] = coordinates.getKey();
                y[i] = coordinates.getValue();
            }

            XYSeries series = chart.addSeries("line " + i, xArr.get(i), yArr.get(i));
            series.setMarker(SeriesMarkers.NONE);
        }

        charts.add(chart);
        new SwingWrapper<XYChart>(charts).displayChartMatrix();
    }

    public static void task3() {
        Random random = new Random();
        double[] chances = new double[6];
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < 500; i++) {
                int tmp = random.nextInt(6);
                switch (tmp) {
                    case 0:
                        chances[0]++;
                        break;
                    case 1:
                        chances[1]++;
                        break;
                    case 2:
                        chances[2]++;
                        break;
                    case 3:
                        chances[3]++;
                        break;
                    case 4:
                        chances[4]++;
                        break;
                    case 5:
                        chances[5]++;
                        break;
                }
            }
            for (double chance : chances) {
                System.out.print(chance/500 + " ");
            }
            System.out.println();
            chances = new double[6];

        }
    }
}
