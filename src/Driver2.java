import models.*;


/*
* 1) TODO:
*
*
* */
public class Driver2
{
	public static final int FROM = 0;
	public static final int TO = 1;

	public static BoostCLI io;
	public static Environment earth;
	public static Environment spaceship;
	public static Environment mars;

	public static void main(String[] args) {
		io = new BoostCLI();
		earth = new Earth();
		spaceship = new Spaceship();
		mars = new Mars();
		Validator.DEBUG = true;

		String select;
		String[] pSelect;

		String move;
		String[] pMove;

		io.pp("\n", 100);

		while (true) {
			io.p().p();
			io.p(earth.toString());
			io.p(spaceship.toString());
			io.p(mars.toString()).p();

			select = io.s(">  Select : ");
			pSelect = select.split(" ");
			pSelect = cleanSelection(pSelect);

			move = io.s(">  Path: ");
			io.pp("-", 30).p().p();
			pMove = move.split(" ");
			io.pp("\n", 100);
			execute(pMove, pSelect);
			io.p();
		}
	}

	public static void execute(String[] fromto, String[] characters) {
		Environment from = toEnv(fromto[FROM]);
		Environment to = toEnv(fromto[TO]);

		if (Validator.canMigrate(characters, from, to)) {
			to.migrate(from, characters);
			io.p("Successful migration to " + to.getName() + ".", "box");
		} else {
			io.p("Invalid migration to " + to.getName() + ".", "box");
		}
	}

	public static String[] cleanSelection(String[] pSelect) {
		String[] cleanSelection = new String[pSelect.length];

		for (int i = 0; i < pSelect.length; i++)
			cleanSelection[i] = toCharacter(pSelect[i]);

		return cleanSelection;
	}

	public static String toCharacter(String ch) {
		if (ch.equalsIgnoreCase("s") || ch.equalsIgnoreCase("scientist"))
			return "Scientist";
		if (ch.equalsIgnoreCase("h1") || ch.equalsIgnoreCase("human1"))
			return "Human1";
		if (ch.equalsIgnoreCase("h2") || ch.equalsIgnoreCase("human2"))
			return "Human2";
		if (ch.equalsIgnoreCase("l") || ch.equalsIgnoreCase("lion"))
			return "Lion";
		if (ch.equalsIgnoreCase("c") || ch.equalsIgnoreCase("cow"))
			return "Cow";
		if (ch.equalsIgnoreCase("g") || ch.equalsIgnoreCase("grain"))
			return "Grain";
		return "#missingcharacterstring";
	}

	public static Environment toEnv(String name) {
		if (name.equalsIgnoreCase("earth") || name.equalsIgnoreCase("e"))
			return earth;
		if (name.equalsIgnoreCase("spaceship") || name.equalsIgnoreCase("s"))
			return spaceship;
		if (name.equalsIgnoreCase("mars") || name.equalsIgnoreCase("m"))
			return mars;
		return null;
	}
}
