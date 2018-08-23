package cspEngine.models;

import java.util.ArrayList;

public class Mars extends Environment
{
	public Mars() {
		super();

		name = "Mars";
		capacity = 6;
		order = 2;
	}

	public Mars(Environment clone) {
		name = "Mars";
		population = new ArrayList<>(clone.getPopulation());
		capacity = 6;
		order = 2;
	}
}
