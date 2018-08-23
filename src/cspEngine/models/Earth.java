package cspEngine.models;

import java.util.ArrayList;

public class Earth extends Environment
{
	public Earth() {
		super();

		name = "Earth";
		capacity = 6;
		order = 0;

		/* Scientist */
		population.add("Scientist");

		/* Humans */
		population.add("Human1");
		population.add("Human2");

		/* Lion */
		population.add("Lion");

		/* Cow */
		population.add("Cow");

		/* Grain */
		population.add("Grain");
	}

	public Earth(Environment clone) {
		name = "Earth";
		population = new ArrayList<>(clone.getPopulation());
		capacity = 6;
		order = 0;
	}
}
