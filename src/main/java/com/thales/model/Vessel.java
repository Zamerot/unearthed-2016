package com.thales.model;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Vessel {

	private final String id;

	private final Dimension dimension;

	public Vessel(String id, Dimension dimension) {
		this.id = checkNotNull(id);
		this.dimension = checkNotNull(dimension);
	}

	public String getId() {
		return id;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public static class Dimension {

		public final int width;
		public final int height;

		public Dimension(int width, int height) {
			this.width = width;
			this.height = height;
		}

	}

}
