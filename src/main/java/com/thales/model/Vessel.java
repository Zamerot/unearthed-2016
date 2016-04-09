package com.thales.model;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Vessel {

	public static final Vessel VESSEL16 = new Vessel("Vessel 16", new Dimension(14, 50));
	public static final Vessel VESSEL7 = new Vessel("Vessel 7", new Dimension(10, 16));
	public static final Vessel VESSEL2 = new Vessel("Vessel 2", new Dimension(11, 19));

	public static final Vessel[] VESSELS = { VESSEL16, VESSEL7, VESSEL2 };

	private final String id;

	private final Dimension dimension;

	private Vessel(String id, Dimension dimension) {
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
		public final int size;

		public Dimension(int width, int height) {
			this.width = width;
			this.height = height;
			size = width * height;
		}

	}

}
