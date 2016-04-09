package com.thales.model;

import static com.google.common.base.Preconditions.checkNotNull;

public class Item {

	private final String name;

	private final Priority priority;

	private final Urgency urgency;

	private final Destination destination;

	public Item(String name, Priority priority, Urgency urgency, Destination destination) {
		this.name = checkNotNull(name);
		this.priority = checkNotNull(priority);
		this.urgency = checkNotNull(urgency);
		this.destination = checkNotNull(destination);
	}

	public String getName() {
		return name;
	}

	public Priority getPriority() {
		return priority;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public Destination getDestination() {
		return destination;
	}

}
