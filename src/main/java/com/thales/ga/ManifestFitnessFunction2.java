package com.thales.ga;

import org.jenetics.EnumGene;
import org.jenetics.Genotype;

import com.google.common.base.Function;
import com.thales.model.Item;
import com.thales.model.Store;
import com.thales.model.Vessel;

public class ManifestFitnessFunction2 implements Function<Genotype<EnumGene<Item>>, Double>{

	public ManifestFitnessFunction2(Vessel vessel, Store store) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double apply(Genotype<EnumGene<Item>> input) {
		
		return null;
	}

}
