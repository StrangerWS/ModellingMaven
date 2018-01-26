package com.strangerws.modelling;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.List;

public class Chart {

    private List<Double> listX;
    private List<Double> listY;

    public Chart(List<Double> listX, List<Double> listY) {
        this.listX = listX;
        this.listY = listY;
    }

    public void make() {
        List<XYChart> charts = new ArrayList<XYChart>();
        XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();

        XYSeries series = chart.addSeries("line", listX, listY);
        series.setMarker(SeriesMarkers.NONE);
        charts.add(chart);
        new SwingWrapper<XYChart>(charts).displayChartMatrix();
    }
}
