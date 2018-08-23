package graphEngine;

import java.util.Scanner;

class Driver {
	public static void main(String[] args) {
		Reference.init();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		while (startMenu() == 0)
			mainGame();

	}

	public static boolean mainGame() {
		Scanner input = new Scanner(System.in);
		String currentState = Reference.START_STATE;
		boolean stepAllFlag = false;
		int turn = 1;

		while (!currentState.equals(Reference.END_STATE)) {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			if (turn == 1)
				help();

			System.out.println("------------------");
			System.out.println("\tTurn " +turn+ "\t");
			System.out.println("------------------");
			showState(currentState);
			System.out.println("Tip: <entity1, entity2, entityN> <source> <destination>");
			System.out.print("==> ");
			String in = "";

			if (!stepAllFlag) {
				in = input.nextLine();
				if (!in.equalsIgnoreCase("stepall") && !in.equalsIgnoreCase("step"))
					in = preprocessInput(in);
			}

			if (in.equalsIgnoreCase("stepall"))
				stepAllFlag = true;
			if (in.equalsIgnoreCase("step") || stepAllFlag) {
				in = Reference.smartChoice(currentState);
				System.out.println(in);
			}

			in = Reference.get(currentState).follow(in);

			if (in == null) {
				System.out.println("You can't do that.\n");
				continue;
			}
			currentState = in;
			turn++;
		}


		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		showState(currentState);
		System.out.println("\n\nYOU WON AT " +(turn-1)+ " TURNS!\n\n\n\n");
		return true;
	}

	public static void showState(String currentState) {
		String envs = Reference.corres.get(currentState);
		String[] envs_ = envs.split("\\|");
		for (int i = 0; i < envs_.length; i++) {
			if (i == 0) {
				System.out.println("Earth:");
				for (int j = 0; j < envs_[i].length(); j++)
					if (!(envs_[i].charAt(j)+"").equals(" "))
						System.out.println("\t" + toCleanCharacter("" + envs_[i].charAt(j)));
				System.out.println();
			}
			else if (i == 1) {
				System.out.println("Spaceship:");
				for (int j = 0; j < envs_[i].length(); j++)
					if (!(envs_[i].charAt(j)+"").equals(" "))
						System.out.println("\t" + toCleanCharacter("" + envs_[i].charAt(j)));
				System.out.println();
			}
			else {
				System.out.println("Mars:");
				for (int j = 0; j < envs_[i].length(); j++)
					if (!(envs_[i].charAt(j)+"").equals(" "))
						System.out.println("\t" + toCleanCharacter("" + envs_[i].charAt(j)));
				System.out.println();
			}
		}
	}

	public static String toCharacter(String ch) {
		if (ch.equalsIgnoreCase("s") || ch.equalsIgnoreCase("scientist"))
			return "S";
		if (ch.equalsIgnoreCase("h") || ch.equalsIgnoreCase("human"))
			return "H";
		if (ch.equalsIgnoreCase("l") || ch.equalsIgnoreCase("lion"))
			return "L";
		if (ch.equalsIgnoreCase("c") || ch.equalsIgnoreCase("cow"))
			return "C";
		if (ch.equalsIgnoreCase("g") || ch.equalsIgnoreCase("grain"))
			return "G";
		return "";
	}

	public static String toCleanCharacter(String ch) {
		if (ch.equalsIgnoreCase("s") || ch.equalsIgnoreCase("scientist"))
			return "Scientist";
		if (ch.equalsIgnoreCase("h") || ch.equalsIgnoreCase("human"))
			return "Human";
		if (ch.equalsIgnoreCase("l") || ch.equalsIgnoreCase("lion"))
			return "Lion";
		if (ch.equalsIgnoreCase("c") || ch.equalsIgnoreCase("cow"))
			return "Cow";
		if (ch.equalsIgnoreCase("g") || ch.equalsIgnoreCase("grain"))
			return "Grain";
		return "";
	}

	public static String toEnv(String name) {
		if (name.equalsIgnoreCase("earth") || name.equalsIgnoreCase("e"))
			return "E";
		if (name.equalsIgnoreCase("spaceship") || name.equalsIgnoreCase("s"))
			return "S";
		if (name.equalsIgnoreCase("mars") || name.equalsIgnoreCase("m"))
			return "M";
		return null;
	}

	public static String preprocessInput(String input) {
		String[] in_ = input.split(" ");

		if ((""+in_[0].charAt(0)).equals("<") && (""+in_[0].charAt(in_[0].length()-1)).equals(">"))
			return in_[0];

		input = "<" + toEnv(in_[in_.length-2]) + toEnv(in_[in_.length-1]);
		input += "-";

		for (int i = 0; i < in_.length-2; i++) {
			String s = in_[i];
			input += toCharacter(s);
		}
		input += ">";
		return input;
	}

	public static int startMenu() {
		Scanner in = new Scanner(System.in);
		int choice = 1;

		while (choice == 1) {
			System.out.println("===============================================");
			System.out.println(" T H E  T R A V E L L I N G  S C I E N T I S T ");
			System.out.println("===============================================");

			System.out.println("Main Menu :");
			System.out.println("[0]\t Play");
			System.out.println("[1]\t How to play");
			System.out.println("[2]\t Exit\n\n");

			System.out.print(">> ");
			choice = in.nextInt();

			if (choice == 0) {
				return 0;
			}
			if (choice == 1) {
				help();
			}
			if (choice == 2) {
				return 2;
			}
		}
		return -1;
	}

	public static void help() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("=====================");
		System.out.println(" H O W  T O  P L A Y ");
		System.out.println("=====================");

		String help = "\n";
		help += "1\t HOW TO PLAY\n";
		help += "\tEvery turn, you are asked where to get people and " +
				"where \n\tto drop them off, and who are involved in the migration.\n\n" +
				"\tInput format : \"<entity1> <entity2> ... <entityN> <source> <destination>\"";
		help += "\n\n\n";
		help += "2\t BACKSTORY\n";
		help += " The Earth is slowly dying and a scientist wants to help build a new life on Mars.\n " +
				"However, he only has a spaceship that was so tiny it could only carry him and two\n " +
				"other items. He wants to transport five things that he thought were essential to\n " +
				"start a new life on Mars: two humans, one lion, one cow and one bag of grain.\n " +
				"Whenever the scientist is not around, either human would kill the lion out of fear\n " +
				"or eat the cow out of hunger,the lion will eat the cow, and the cow will eat the\n " +
				"grain. Only the scientist knows how to fly the spaceship. How will the scientist\n " +
				"transport all the five items to Mars?\n\n\n\n\n\n\n\n\n\n";

		System.out.println(help);
	}
}
