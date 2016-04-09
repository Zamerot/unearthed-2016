package com.thales.model;

public final class Vessel {

	private final String id;

	private final Dimension dimension;

	public Vessel(String id, Dimension dimension) {
		//this.id = checkNotNull(id);
		this.id = id;
//		this.dimension = checkNotNull(dimension);
		this.dimension = dimension;
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
