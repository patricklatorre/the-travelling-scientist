package cspEngine;

import cspEngine.models.*;


/*
* TODO: 1) should not be able to migrate from E to M or S to M
* TODO: 2) validator should also check FROM environment for safety
* TODO: 3) SELECT characters to move before inputting FROM and TO
* */
public class Driver1
{
	public static final int CHAR = 0;
	public static final int FROM = 1;
	public static final int TO = 2;

	public static BoostCLI io;
	public static Environment earth;
	public static Environment spaceship;
	public static Environment mars;

	public static void main(String[] args) {
		io = new BoostCLI();
		earth = new Earth();
		spaceship = new Spaceship();
		mars = new Mars();

		String in;
		String[] pIn;

		while (true) {
			io.p().p();
			io.p(earth.toString());
			io.p(spaceship.toString());
			io.p(mars.toString()).p();

			io.p("<character> <from> <to>");
			in = io.s("Move: ");
			pIn = in.split(" ");

			if (contains(pIn[CHAR], pIn[FROM])) {
				Environment from = toEnv(pIn[FROM]);
				Environment to = toEnv(pIn[TO]);

				if (Validator.canMigrate(pIn[CHAR], from, to)) {
					to.migrate(from, pIn[CHAR]);
					io.p(pIn[CHAR] + " migrated to " + to.getName() + ".", "box");
				} else {
					io.p("You can't migrate " + pIn[CHAR] + " to " + to.getName() + ".", "box");
				}
			}
		}
	}

	public static boolean contains(String character, String environment) {
		if (toEnv(environment).getPopulation().contains(character))
			return true;
		else
			return false;
	}

	public static Environment toEnv(String name) {
		if (name.equalsIgnoreCase("Earth") || name.equalsIgnoreCase("E"))
			return earth;
		if (name.equalsIgnoreCase("Spaceship") || name.equalsIgnoreCase("S"))
			return spaceship;
		if (name.equalsIgnoreCase("Mars") || name.equalsIgnoreCase("M"))
			return mars;
		return null;
	}
}
