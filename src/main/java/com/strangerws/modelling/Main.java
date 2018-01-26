package com.strangerws.modelling;

import javafx.util.Pair;

import java.util.List;

import static com.strangerws.modelling.Task.task1;

public class Main {
    //вариант 4


    public static void main(String[] args) {
        Pair<List<Double>, List<Double>> result = task1();
        new Chart(result.getKey(), result.getValue()).make();
    }
}
