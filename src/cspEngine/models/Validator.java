package cspEngine.models;

import java.util.ArrayList;
import java.util.List;

public class Validator
{
	public static boolean DEBUG = true;
	public static boolean canMigrate(String character, Environment from, Environment to) {
		if (	from.getPopulation().contains(character)
				&& to.getPopulation().size() < to.getCapacity()
				&& isTraversable(from, to)
				&& safeToMigrate(character, to)
				&& safeToLeave(character, from)) {
			if (DEBUG) System.out.println("----CAN MIGRATE " + character);
			return true;
		} else {
			System.out.println("----CANNOT MIGRATE " + character);
			return false;
		}
	}

	public static boolean canMigrate(String[] selection, Environment from, Environment to) {
		Environment fromClone = ambiguousClone(from);
		Environment toClone = ambiguousClone(to);

		/* Check if traversal is valid */
		if (!isTraversable(from, to)) {
			if (DEBUG) System.out.println("----NOT TRAVERSABLE");
			return false;
		}

		/* Don't continue if overpopulated destination */
		if (to.getPopulation().size() + selection.length > to.getCapacity()) {
			if (DEBUG) System.out.println("----OVERPOPULATED DEST");
			return false;
		}

		/* Check if selection is valid */
		if (!validateSelection(selection, fromClone)) {
			if (DEBUG) System.out.println("----INVALID SELECTION");
			return false;
		}

		/* Check if it is a scientist convoy */
		boolean isScientistSelected = isInCharacterList(selection, "Scientist");

		/* Scientist convoy */
		if (isScientistSelected) {
			if (DEBUG) System.out.println("----SCIENTIST SELECTED");
			/* Safe transfer */
			fromClone.getPopulation().add("Scientist");
			toClone.getPopulation().add("Scientist");

			/* Migrate all non-scientists */
			for (int i = 0; i < selection.length; i++)
				if (!selection[i].equalsIgnoreCase("Scientist"))
					toClone.migrate(fromClone, selection[i]);

			/* Check if scientist is safe to leave */
			if (safeToLeave("Scientist", fromClone))
				return true;
			else
				return false;
		}
		else {
			if (DEBUG) System.out.println("----SCIENTIST NOT SELECTED");
			for (int i = 0; i < selection.length; i++) {
				if (!canMigrate(selection[i], fromClone, toClone)) {
					if (DEBUG) System.out.println("----NOT SCI-CONVOY, ENTITY CONFLICTS WITH DESTINATION");
					return false;
				}
				toClone.migrate(fromClone, selection[i]);
			}
		}

		return true;
	}

	public static boolean validateSelection(String[] characters, Environment env) {
		for (String character : characters)
			if (!env.getPopulation().contains(character))
				return false;
		return true;
	}

	public static boolean isInCharacterList(String[] characters, String character) {
		for (String ch : characters)
			if (character.equalsIgnoreCase(ch))
				return true;
		return false;
	}

	public static boolean removeCharacters(String[] characters, Environment from) {
		for (String character : characters) {
			boolean success = from.getPopulation().remove(character);
			if (!success)
				return false;
		}
		return true;
	}

	public static boolean safeToLeave(String character, Environment from) {
		List<String> popu = from.getPopulation();
		if (!character.equalsIgnoreCase("Scientist")) {
			return true;
		} else {
			for (int i = 0; i < popu.size(); i++)
				for (int j = i; j < popu.size(); j++)
					if (isDangerous(popu.get(i), popu.get(j))
							&& !popu.get(i).equalsIgnoreCase(character)
							&& !popu.get(j).equalsIgnoreCase(character))
						return false;
		}

		return true;
	}

	public static boolean safeToMigrate(String character, Environment to) {
		if (to.getPopulation().contains("Scientist")) {
			if (DEBUG) System.out.println("----destination contains scientists");
			return true;
		}

		List<String> popuClone = new ArrayList<>(to.getPopulation());

		for (String member : popuClone) {
			if (isDangerous(character, member)) {
				return false;
			}

			System.out.println("----" + member + " not dangerous");
		}

		return true;
	}

	public static boolean isDangerous(String character, String member) {
		if (character.equals("Scientist")) {
			if (DEBUG) System.out.println("----Scientist not dangerous");
			return false;
		}
		if (	(character.equals("Human1") || character.equals("Human2"))
				&& member.equals("Lion")
				|| character.equals("Lion")
				&& (member.equals("Human1") || member.equals("Human2"))) {
			if (DEBUG) System.out.println("----HUMAN LION IN DANGER");
			return true;
		}
		if (	(character.equals("Human1") || character.equals("Human2"))
				&& member.equals("Cow")
				|| character.equals("Cow")
				&& (member.equals("Human1") || member.equals("Human2"))) {
			if (DEBUG) System.out.println("----HUMAN COW IN DANGER");
			return true;
		}
		if (	character.equals("Lion")
				&& member.equals("Cow")
				|| character.equals("Cow")
				&& member.equals("Lion")) {
			if (DEBUG) System.out.println("----LION COW IN DANGER");
			return true;
		}
		if (	character.equals("Grain")
				&& member.equals("Cow")
				|| character.equals("Cow")
				&& member.equals("Grain")) {
			return true;
		}
		return false;
	}

	private static boolean isTraversable(Environment from, Environment to) {
		int aFrom = Math.abs(from.getOrder());
		int aTo = Math.abs(to.getOrder());

		if (aFrom == 1) {
			if (from.getPopulation().contains("Scientist") && aFrom != aTo) {
					return true;
			} else {
				return false;
			}
		} else {
			if (aFrom-aTo == 1 || aTo-aFrom == 1)
				return true;
			else
				return false;
		}
	}

	public static Environment ambiguousClone(Environment source) {
		if (source instanceof Earth)
			return new Earth(source);
		if (source instanceof Spaceship)
			return new Spaceship(source);
		if (source instanceof Mars)
			return new Mars(source);
		return null;
	}
}
