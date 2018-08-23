package cspEngine.models;

import java.util.ArrayList;

public class Spaceship extends Environment
{
	public Spaceship() {
		super();

		name = "Spaceship";
		capacity = 3;
		order = 1;
	}

	public Spaceship(Environment clone) {
		name = "Spaceship";
		population = new ArrayList<>(clone.getPopulation());
		capacity = 3;
		order = 1;
	}
}
