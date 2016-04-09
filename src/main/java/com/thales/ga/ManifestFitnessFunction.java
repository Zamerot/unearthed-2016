package com.thales.ga;

import java.util.function.Function;
import java.util.stream.Collector;

import org.jenetics.BitChromosome;
import org.jenetics.BitGene;
import org.jenetics.Genotype;

import com.thales.model.Item;
import com.thales.model.Store;

public class ManifestFitnessFunction implements Function<Genotype<BitGene>, Double> {

	private final Store store;
	private final double size;

	public ManifestFitnessFunction(Store store, double size) {
		this.store = store;
		this.size = size;
	}

	private Collector<Item, double[], double[]> sum() {
		return Collector.of(() -> new double[3], (a, b) -> {
			a[0] += 255 - b.getPriority().getValue();
			a[1] += 255 - b.getUrgency().getValue();
			a[2]++;
		} , (a, b) -> {
			a[0] += b[0];
			a[1] += b[1];
			a[2] += b[2];
			return a;
		} , r -> r);
	}

	public Double apply(Genotype<BitGene> gt) {
		double[] value = ((BitChromosome) gt.getChromosome()).ones().mapToObj(i -> store.getItem(i)).collect(sum());
		return value[2] <= this.size ? (value[0] + value[1]) : 0;
	}

}
