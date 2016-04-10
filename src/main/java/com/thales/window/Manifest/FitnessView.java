package com.thales.window.Manifest;

import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.animation.AnimationTimer;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

/**
 * @author Will Crisp
 */
public class FitnessView extends Pane {

	private static final int MAX_DATA_POINTS = 100;

	private XYChart.Series<Number, Number> fitnessSeries;
	private XYChart.Series<Number, Number> meanSeries;
	private int fitnessData = 0;
	private ConcurrentLinkedQueue<Number> fitness = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Number> mean = new ConcurrentLinkedQueue<>();
	private NumberAxis xAxis;

	public FitnessView() {

		initGraph();
		prepareTimeline();



	}

	// -- Timeline gets called in the JavaFX Main thread
	private void prepareTimeline() {
		// Every frame to take any data from queue and add to chart
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				addDataToSeries();
			}
		}.start();
	}

	private void initGraph() {
		xAxis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS / 10);
		xAxis.setForceZeroInRange(false);
		xAxis.setAutoRanging(false);

		NumberAxis yAxis = new NumberAxis();
		yAxis.setAutoRanging(true);

		// -- Chart Series
		fitnessSeries = new AreaChart.Series<Number, Number>();
		fitnessSeries.setName("Cost");

		meanSeries = new AreaChart.Series<Number, Number>();
		meanSeries.setName("Mean Cost");

		// -- Chart
		final AreaChart<Number, Number> sc = new AreaChart<Number, Number>(xAxis, yAxis) {
			// Override to remove symbols on each data point
			@Override
			protected void dataItemAdded(Series<Number, Number> series, int itemIndex, Data<Number, Number> item) {
			}
		};
		sc.setAnimated(false);
		sc.setId("liveFitnessGraph");
		sc.setTitle("Live Fitness Graph");

		sc.getData().add(fitnessSeries);
		sc.getData().add(meanSeries);
		getChildren().add(sc);
	}

	private void addDataToSeries() {
		for (int i = 0; i < 20; i++) { // -- add 20 numbers to the plot+
			if (fitness.isEmpty()) {
				break;
			}
			fitnessSeries.getData().add(new AreaChart.Data(fitnessData++, fitness.remove()));
			meanSeries.getData().add(new AreaChart.Data(fitnessData, mean.remove()));
		}
		// remove points to keep us at no more than MAX_DATA_POINTS
		if (fitnessSeries.getData().size() > MAX_DATA_POINTS) {
			fitnessSeries.getData().remove(0, fitnessSeries.getData().size() - MAX_DATA_POINTS);
			meanSeries.getData().remove(0, meanSeries.getData().size() - MAX_DATA_POINTS);
		}
		// update
		xAxis.setLowerBound(fitnessData - MAX_DATA_POINTS);
		xAxis.setUpperBound(fitnessData - 1);
	}

	public void addDataToQueue(double cost, double mean) {
		this.fitness.add(cost);
		this.mean.add(mean);
	}

}
