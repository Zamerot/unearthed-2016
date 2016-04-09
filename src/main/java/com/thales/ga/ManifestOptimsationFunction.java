package com.thales.ga;

import org.jenetics.engine.EvolutionStatistics;

import com.thales.model.Manifest;

@FunctionalInterface
public interface ManifestOptimsationFunction {

	void apply(long generation, EvolutionStatistics statistics, Manifest manifest);
	
}
