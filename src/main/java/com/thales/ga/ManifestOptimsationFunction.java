package com.thales.ga;

import java.util.List;

import org.jenetics.engine.EvolutionStatistics;

import com.thales.model.Manifest;

@FunctionalInterface
public interface ManifestOptimsationFunction {

	void apply(long generation, EvolutionStatistics statistics, List<Manifest> manifest);
	
}
