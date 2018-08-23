package cspEngine.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Environment
{
	protected String name;
	protected List<String> population;
	protected int capacity;
	protected int order;

	public Environment() {
		name = "#";
		population = new ArrayList<>();
	}

	public void migrate(Environment from, String character) {
		from.getPopulation().remove(character);
		population.add(character);
	}

	public void migrate(Environment from, String... characters) {
		for (int i = 0; i < characters.length; i++) {
			from.getPopulation().remove(characters[i]);
			population.add(characters[i]);
		}
	}

	/* GETTERS AND SETTERS */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPopulation() {
		return population;
	}

	public void setPopulation(List<String> population) {
		this.population = population;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		String str = "";
		str += name + " :\n";

		for (String member : population) {
			str += "\t" + member + "\n";
		}

		return str;
	}
}
