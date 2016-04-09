package com.thales.model;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Vessel {
	
	private static final CargoValidator DEFAULT_VALIDATOR = new DefaultCargoValidator();

	public static final Vessel VESSEL16 = new Vessel("Vessel 16", new Dimension(14, 50), DEFAULT_VALIDATOR);
	public static final Vessel VESSEL7 = new Vessel("Vessel 7", new Dimension(10, 16), DEFAULT_VALIDATOR);
	public static final Vessel VESSEL2 = new Vessel("Vessel 2", new Dimension(11, 19), DEFAULT_VALIDATOR);

	public static final Vessel[] VESSELS = { VESSEL16, VESSEL7, VESSEL2 };

	private final String id;

	private final Dimension dimension;

	private final CargoValidator validator;
	
	private Vessel(String id, Dimension dimension, CargoValidator validator) {
		this.id = checkNotNull(id);
		this.dimension = checkNotNull(dimension);
		this.validator = checkNotNull(validator);
	}

	public String getId() {
		return id;
	}

	public Dimension getDimension() {
		return dimension;
	}
	
	public CargoValidator getCargoValidator() {
		return validator;
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
	
	@FunctionalInterface
	private static interface CargoValidator {
		
		boolean check(Item item, int x, int y);
		
	}
	
	private static final class DefaultCargoValidator implements CargoValidator {

		@Override
		public boolean check(Item item, int x, int y) {
			return true;
		}
		
	}

}
