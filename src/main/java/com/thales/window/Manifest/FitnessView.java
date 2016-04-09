package com.thales.window.Manifest;


import javafx.animation.AnimationTimer;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * @author Will Crisp
 */
public class FitnessView extends Pane {


  private static final int MAX_DATA_POINTS = 100;

  private XYChart.Series series;
  private int xSeriesData = 0;
  private ConcurrentLinkedQueue<Number> dataQ = new ConcurrentLinkedQueue<>();
  private NumberAxis xAxis;

  public FitnessView() {

    initGraph();
    prepareTimeline();

    this.setStyle("-fx-background-color: #CCFF99;");

  }
  //-- Timeline gets called in the JavaFX Main thread
  private void prepareTimeline() {
    // Every frame to take any data from queue and add to chart
    new AnimationTimer() {
      @Override public void handle(long now) {
        addDataToSeries();
      }
    }.start();
  }

  private void initGraph(){
    xAxis = new NumberAxis(0,MAX_DATA_POINTS,MAX_DATA_POINTS/10);
    xAxis.setForceZeroInRange(false);
    xAxis.setAutoRanging(false);

    NumberAxis yAxis = new NumberAxis(10000000,15000000,100000);
    yAxis.setAutoRanging(false);

    //-- Chart
    final AreaChart<Number, Number> sc = new AreaChart<Number, Number>(xAxis, yAxis) {
      // Override to remove symbols on each data point
      @Override protected void dataItemAdded(Series<Number, Number> series, int itemIndex, Data<Number, Number> item) {}
    };
    sc.setAnimated(false);
    sc.setId("liveFitnessGraph");
    sc.setTitle("Live Fitness Graph");

    //-- Chart Series
    series = new AreaChart.Series<Number, Number>();
    series.setName("fitnessGraph Series");
    sc.getData().add(series);
    getChildren().add(sc);
  }

  private void addDataToSeries() {
    for (int i = 0; i < 20; i++) { //-- add 20 numbers to the plot+
      if (dataQ.isEmpty()) break;
      series.getData().add(new AreaChart.Data(xSeriesData++, dataQ.remove()));
    }
    // remove points to keep us at no more than MAX_DATA_POINTS
    if (series.getData().size() > MAX_DATA_POINTS) {
      series.getData().remove(0, series.getData().size() - MAX_DATA_POINTS);
    }
    // update
    xAxis.setLowerBound(xSeriesData - MAX_DATA_POINTS);
    xAxis.setUpperBound(xSeriesData-1);
  }


  public void addDataToQueue(double fitnessValue){
    dataQ.add(fitnessValue);
  }

}
