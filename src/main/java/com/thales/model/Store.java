package com.thales.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jenetics.util.ISeq;

public final class Store {

	private final List<Item> items = Collections.synchronizedList(new ArrayList<Item>());

	public void addItem(Item item) {
		items.add(item);
	}

	public Item getItem(int index) {
		return items.get(index);
	}

	public int size() {
		return items.size();
	}

	public List<Item> allItems() {
		return new LinkedList<Item>(items);
	}

}
