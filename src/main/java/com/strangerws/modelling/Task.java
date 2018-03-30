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
    }//+

    public static void task2() {
        List<List<Double>> xArr = new ArrayList<List<Double>>();
        List<List<Double>> yArr = new ArrayList<List<Double>>();

        double a1 = -2d;
        double a2 = -8d;

        double t;
        double[] x = {1, -1, -1, 1, 0.55};
        double[] y = {1, 1, -1, -1, 2};

        Pair<Double, Double> coordinates;
        List<XYChart> charts = new ArrayList<XYChart>();
        XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(800).height(600).build();

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
    }//+

    public static void task3() {
        Random random = new Random();
        double tmp = 0;
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < 500; i++) {
                tmp += (random.nextInt(6) + 1);
            }
            System.out.println(tmp / 500);
            tmp = 0;
        }
    }//+;

    public static void task4() {
        double period = 1;//period = 1 week = 10080
        Random random = new Random();
        double[] accuracy = new double[3];
        double accuracyAverage = 0;
        double mu = 0;
        double sigma = 1;
        double experiments = 1000;
        double counter = 0;

        for (int k = 0; k < experiments; k++) {
            for (int i = 0; i < accuracy.length; i++) {
                double tmpX = 0;
                for (int j = 0; j < period; j++) {
                    double tmp = 0;
                    for (int l = 1; l <= 12; l++) {
                        tmp += random.nextDouble();
                    }
                    tmpX += mu + sigma * (tmp - 6);
                }
                accuracy[i] = tmpX; //writing accuracy for each chronometer
                accuracyAverage += tmpX; // incrementing average accuracy
            }
            accuracyAverage /= accuracy.length; //subtracting accuracy by chronometer count - getting expectation
            for (int i = 0; i < accuracy.length; i++) {
                if (Math.abs(accuracyAverage - accuracy[i]) > 2) {
                    counter++;
                    break;
                }
            }
        }
        System.out.println(counter / experiments); //getting probability
    }

    public static void task5() {
        Random random = new Random();
        double h = 10;
        double experiments = 1000;
        double lambda = 0.35;
        double probability = 0.5;
        double length = 0;
        double counter = 0;

        for (int i = 0; i < experiments; i++) {
            length = 0;
            for (int j = 0; j < h; j++) {
                if (probability >= random.nextDouble()) {
                    length += (-1 / lambda) * Math.log(random.nextDouble());
                }
            }
            if (length >= h) {
                counter++;
            }

        }
        System.out.println(counter / experiments);
    }
}