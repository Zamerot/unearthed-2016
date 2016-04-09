package com.thales.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manifest {

	private final List<Item> items = Collections.synchronizedList(new ArrayList<Item>());

	private final Vessel vessel;

	public Manifest(Vessel vessel) {
//		this.vessel = checkNotNull(vessel);
		this.vessel = vessel;
	}

	public Vessel getVessel() {
		return vessel;
	}

	public void addItem(Item item) {
//		items.add(checkNotNull(item));
		items.add(item);
	}

}
