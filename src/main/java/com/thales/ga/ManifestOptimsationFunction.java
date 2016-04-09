package com.thales.ga;

import com.thales.model.Manifest;

@FunctionalInterface
public interface ManifestOptimsationFunction {

	void apply(long generation, Manifest manifest);
	
}
