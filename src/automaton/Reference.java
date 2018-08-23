package automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Node {
	private String name;
	private List<Path> paths;
	private int distanceFromFinal;

	public Node(String name, int distanceFromFinal) {
		this.name = name;
		this.distanceFromFinal = distanceFromFinal;
		paths = new ArrayList<>();
	}

	public void point(String edges) {
		String[] properties = edges.split(" ");
		for (int i = 0; i < properties.length - 1; i = i + 2)
			paths.add(new Path(properties[i], properties[i + 1]));
	}

	public Node(Node node) {
		name = node.name();
		paths = new ArrayList<>(node.paths());
		distanceFromFinal = node.dist();
	}

	public String name() {
		return name;
	}

	public int dist() {
		return distanceFromFinal;
	}

	public List<Path> paths() {
		return paths;
	}

	public String follow(String path) {
		for (Path p : paths)
			if (p.path().equalsIgnoreCase(path))
				return p.dest();
		return null;
	}

	@Override
	public String toString() {
		String str = "";
		str += name + ":\n";
		for (Path p : paths)
			str += "\t" + p.toString() + "\n";
		return str;
	}

	@Override
	public Node clone() {
		return new Node(this);
	}
}

class Path {
	private String path;
	private String dest;

	public Path(String path, String dest) {
		this.path = path;
		this.dest = dest;
	}

	public String path() {
		return path;
	}

	public String dest() {
		return dest;
	}

	@Override
	public String toString() {
		return "" + path +" "+ dest;
	}
}

public class Reference {
	public static HashMap<String, Node> map;
	public static HashMap<String, String> corres;
	public static String START_STATE = "Q0";
	public static String END_STATE = "Q10";

	public static void init() {
		map = new HashMap<>();
		corres = new HashMap<>();

		String name;
		Node node;

		corres.put("Q0", "SHHLCG | - | -");
		corres.put("Q1", "HHG | SLC | -");
		corres.put("Q2", "HHG | SC | L");
		corres.put("Q3", "HH | SCG | L");
		corres.put("Q4", "HH | SC | LG");
		corres.put("Q5", "SCHH | - | LG");
		corres.put("Q6", "C | SHH | LG");
		corres.put("Q7", "C | - | SLGHH");
		corres.put("Q8", "C | SL | HHG");
		corres.put("Q9", "- | SLC | HHG");
		corres.put("Q10", "- | - | SHHLCG");
		corres.put("Q11", "HHG | SL | C");
		corres.put("Q12", "HHG | - | SLC");
		corres.put("Q13", "SCHHG | - | L");
		corres.put("Q14", "SHHG | C | L");
		corres.put("Q15", "HH | - | SLCG");
		corres.put("Q16", "HH | C | SLG");
		corres.put("Q17", "HH | G | SCL");
		corres.put("Q18", "HG | SCH | L");
		corres.put("Q19", "HHG | C | SL");
		corres.put("Q20", "SHHLG | C | -");
		corres.put("Q21", "SHHCG | L | -");
		corres.put("Q22", "SLC | HHG | -");
		corres.put("Q23", "SLCG | HH | -");
		corres.put("Q24", "SHLC | HG | -");
		corres.put("Q25", "SHHLC | G | -");
		corres.put("Q26", "SHH | C | LG");
		corres.put("Q27", "H | SCH | LG");
		corres.put("Q28", "SC | HH | LG");
		corres.put("Q29", "SCH | H | LG");
		corres.put("Q30", "C | HH | SLG");
		corres.put("Q31", "C | H | SHLG");
		corres.put("Q32", "C | HHG | SL");
		corres.put("Q33", "C | L | SHHG");
		corres.put("Q34", "C | LG | SHH");
		corres.put("Q35", "C | G | SHHL");
		corres.put("Q36", "C | SLG | HH");
		corres.put("Q37", "SLC | - | HHG");
		corres.put("Q38", "SC | L | HHG");
		corres.put("Q39", "SL | C | HHG");
		corres.put("Q40", "L | SC | HHG");
		corres.put("Q41", "- | LC | SHHG");
		corres.put("Q42", "LC | S | HHG");
		corres.put("Q43", "- | L | SHHCG");
		corres.put("Q44", "- | C | SHHLG");
		corres.put("Q45", "SHLCG | H | -");
		corres.put("Q46", "SHHC | LG | -");

		// Q0
		name = "Q0";
		node = new Node(name, 10);
		node.point("<ES-C> Q20 " +
				"<ES-SLC> Q1 " +
				"<ES-L> Q21 " +
				"<ES-LG> Q46 " +
				"<ES-G> Q25 " +
				"<ES-HHG> Q22 " +
				"<ES-HH> Q23 " +
				"<ES-H> Q45 " +
				"<ES-HG> Q24 "
		);
		map.put(name, node);


		// Q20
		name = "Q20";
		node = new Node(name, 10);
		node.point("<SE-C> Q0 " +
				"<ES-SL> Q1 "
		);
		map.put(name, node);


		// Q21
		name = "Q21";
		node = new Node(name, 10);
		node.point("<SE-L> Q0 " +
				"<ES-G> Q46 " +
				"<ES-SC> Q1 "
		);
		map.put(name, node);

		// Q46
		name = "Q46";
		node = new Node(name, 11);
		node.point("<SE-LG> Q0 " +
				"<ES-L> Q25 " +
				"<SE-G> Q21 "
		);
		map.put(name, node);

		// Q25
		name = "Q25";
		node = new Node(name, 11);
		node.point("<SE-G> Q0 " +
				"<SE-L> Q46 " +
				"<ES-HH> Q22 " +
				"<ES-H> Q24 "
		);
		map.put(name, node);

		// Q22
		name = "Q22";
		node = new Node(name, 11);
		node.point("<SE-G> Q23 " +
				"<SE-HG> Q45 " +
				"<SE-H> Q24 " +
				"<SE-HHG> Q0 " +
				"<SE-HH> Q25 "
		);
		map.put(name, node);


		// Q23
		name = "Q23";
		node = new Node(name, 11);
		node.point("<SE-HH> Q0 " +
				"<ES-G> Q22 " +
				"<SE-H> Q45 "
		);
		map.put(name, node);


		// Q45
		name = "Q45";
		node = new Node(name, 11);
		node.point("<ES-H> Q23 " +
				"<ES-G> Q24 " +
				"<SE-H> Q0 " +
				"<ES-HG> Q22 "
		);
		map.put(name, node);


		// q24
		name = "Q24";
		node = new Node(name, 11);
		node.point("<SE-G> Q45 " +
				"<ES-H> Q22 " +
				"<SE-H> Q25 " +
				"<SE-HG> Q0 "
		);
		map.put(name, node);

		// Q1
		name = "Q1";
		node = new Node(name, 9);
		node.point("<SE-SC> Q21 " +
				"<SE-SLC> Q0 " +
				"<SE-SL> Q20 " +
				"<SM-C> Q11 " +
				"<SM-SLC> Q12 " +
				"<SM-L> Q2 "
		);
		map.put(name, node);


		// q11
		name = "Q11";
		node = new Node(name, 10);
		node.point("<MS-C> Q1 " +
				"<SM-SL> Q12 "
		);
		map.put(name, node);


		// Q12
		name = "Q12";
		node = new Node(name, 9);
		node.point("<MS-SLC> Q1 " +
				"<MS-SL> Q11 " +
				"<MS-C> Q19 " +
				"<MS-SC> Q2 "
		);
		map.put(name, node);


		// Q19
		name = "Q19";
		node = new Node(name, 9);
		node.point("<SM-C> Q12 " +
				"<MS-S> Q2 "
		);
		map.put(name, node);


		// Q2
		name = "Q2";
		node = new Node(name, 8);
		node.point("<SM-SC> Q12 " +
				"<MS-L> Q1 " +
				"<SM-S> Q19 " +
				"<ES-G> Q3 " +
				"<ES-H> Q18 " +
				"<SE-S> Q14 " +
				"<SE-SC> Q13 "
		);
		map.put(name, node);


		// Q18
		name = "Q18";
		node = new Node(name, 9);
		node.point("<SE-H> Q2 " +
				"<SE-SH> Q14 " +
				"<SE-SCH> Q13 "
		);
		map.put(name, node);


		// Q14
		name = "Q14";
		node = new Node(name, 8);
		node.point("<ES-SH> Q18 " +
				"<ES-S> Q2 " +
				"<ES-SG> Q3 " +
				"<SE-C> Q13 "
		);
		map.put(name, node);


		// Q13
		name = "Q13";
		node = new Node(name, 8);
		node.point("<ES-SCH> Q18 " +
				"<ES-C> Q14 " +
				"<ES-SC> Q2 " +
				"<ES-SCG> Q3 "
		);
		map.put(name, node);


		// Q3
		name = "Q3";
		node = new Node(name, 7);
		node.point("<SE-G> Q2 " +
				"<SE-SG> Q14 " +
				"<SE-SCG> Q13 " +
				"<SM-SC> Q17 " +
				"<SM-SCG> Q15 " +
				"<SM-SG> Q16 " +
				"<SM-G> Q4 "
		);
		map.put(name, node);


		// Q17
		name = "Q17";
		node = new Node(name, 8);
		node.point("<SM-G> Q15 " +
				"<MS-SC> Q3 "
		);
		map.put(name, node);


		// Q15
		name = "Q15";
		node = new Node(name, 7);
		node.point("<MS-G> Q17 " +
				"<MS-SCG> Q3 " +
				"<MS-SC> Q4 " +
				"<MS-C> Q16 "
		);
		map.put(name, node);


		// Q16
		name = "Q16";
		node = new Node(name, 7);
		node.point("<SM-C> Q15 " +
				"<MS-SG> Q3 " +
				"<MS-S> Q4 "
		);
		map.put(name, node);


		// Q4
		name = "Q4";
		node = new Node(name, 6);
		node.point("<MS-G> Q3 " +
				"<SM-S> Q16 " +
				"<SE-SC> Q5 " +
				"<ES-H> Q27 " +
				"<ES-S> Q26 "
		);
		map.put(name, node);


		// Q26
		name = "Q26";
		node = new Node(name, 6);
		node.point("<SE-S> Q4 " +
				"<ES-SH> Q27 " +
				"<SE-C> Q5 "
		);
		map.put(name, node);


		// Q27
		name = "Q27";
		node = new Node(name, 6);
		node.point("<SE-SH> Q26 " +
				"<SE-H> Q4 " +
				"<ES-SCH> Q5 "
		);
		map.put(name, node);


		// Q5
		name = "Q5";
		node = new Node(name, 5);
		node.point("<SE-SCH> Q27 " +
				"<ES-C> Q26 " +
				"<ES-SC> Q4 " +
				"<ES-HH> Q28 " +
				"<ES-H> Q29 " +
				"<ES-SHH> Q6 "
		);
		map.put(name, node);


		// Q28
		name = "Q28";
		node = new Node(name, 5);
		node.point("<SE-HH> Q5 " +
				"<ES-S> Q6 " +
				"<SE-H> Q29 "
		);
		map.put(name, node);


		// Q29
		name = "Q29";
		node = new Node(name, 5);
		node.point("<ES-H> Q28 " +
				"<SE-H> Q5 " +
				"<ES-SH> Q6 "
		);
		map.put(name, node);


		// Q6
		name = "Q6";
		node = new Node(name, 4);
		node.point("<SE-SH> Q29 " +
				"<SE-S> Q28 " +
				"<SE-SHH> Q5 " +
				"<SM-S> Q30 " +
				"<SM-SH> Q31 " +
				"<SM-SHH> Q7 "
		);
		map.put(name, node);


		// Q30
		name = "Q30";
		node = new Node(name, 4);
		node.point("<MS-S> Q6 " +
				"<SM-HH> Q7 " +
				"<SM-H> Q31 "
		);
		map.put(name, node);


		// Q31
		name = "Q31";
		node = new Node(name, 4);
		node.point("<MS-H> Q30 " +
				"<MS-SH> Q6 " +
				"<SM-H> Q7 "
		);
		map.put(name, node);


		// Q7
		name = "Q7";
		node = new Node(name, 3);
		node.point("<MS-H> Q31 " +
				"<MS-HH> Q30 " +
				"<MS-SHH> Q6 " +
				"<MS-SL> Q8 " +
				"<MS-HHG> Q32 " +
				"<MS-G> Q35 " +
				"<MS-LG> Q34 " +
				"<MS-SLG> Q36 " +
				"<MS-L> Q33 "
		);
		map.put(name, node);


		// Q32
		name = "Q32";
		node = new Node(name, 4);
		node.point("<SM-HHG> Q7 " +
				"<SM-HH> Q35 "
		);
		map.put(name, node);


		// Q35
		name = "Q35";
		node = new Node(name, 4);
		node.point("<MS-HH> Q32 " +
				"<SM-L> Q34 " +
				"<SM-G> Q7 " +
				"<MS-SL> Q36 "
		);
		map.put(name, node);


		// Q34
		name = "Q34";
		node = new Node(name, 4);
		node.point("<MS-L> Q35 " +
				"<SM-LG> Q7 " +
				"<MS-S> Q36 " +
				"<MS-G> Q33 "
		);
		map.put(name, node);


		// Q36
		name = "Q36";
		node = new Node(name, 3);
		node.point("<SM-SLG> Q7 " +
				"<SM-SL> Q35 " +
				"<SM-S> Q34 " +
				"<SM-SG> Q33 " +
				"<SM-G> Q8 "
		);
		map.put(name, node);


		// Q33
		name = "Q33";
		node = new Node(name, 3);
		node.point("<SM-G> Q34 " +
				"<MS-SG> Q36 " +
				"<SM-L> Q7 " +
				"<MS-S> Q8 "
		);
		map.put(name, node);


		// Q8
		name = "Q8";
		node = new Node(name, 2);
		node.point("<SM-SL> Q7 " +
				"<MS-G> Q36 " +
				"<SM-S> Q33 " +
				"<ES-C> Q9 " +
				"<SE-S> Q38 " +
				"<SE-SL> Q37 " +
				"<SE-L> Q42 "
		);
		map.put(name, node);


		// Q38
		name = "Q38";
		node = new Node(name, 2);
		node.point("<ES-S> Q8 " +
				"<ES-SC> Q9 " +
				"<SE-L> Q37 "
		);
		map.put(name, node);


		// Q37
		name = "Q37";
		node = new Node(name, 2);
		node.point("<ES-L> Q38 " +
				"<ES-SL> Q8 " +
				"<ES-SLC> Q9 " +
				"<ES-C> Q39 " +
				"<ES-SC> Q40 " +
				"<ES-S> Q42 "
		);
		map.put(name, node);


		// Q42
		name = "Q42";
		node = new Node(name, 2);
		node.point("<SE-S> Q37 " +
				"<ES-L> Q8 " +
				"<ES-LC> Q9 " +
				"<ES-C> Q40 "
		);
		map.put(name, node);


		// Q39
		name = "Q39";
		node = new Node(name, 2);
		node.point("<SE-C> Q37 " +
				"<ES-SL> Q9 " +
				"<SE-S> Q40 "
		);
		map.put(name, node);


		// Q40
		name = "Q40";
		node = new Node(name, 2);
		node.point("<SE-C> Q42 " +
				"<SE-SC> Q37 " +
				"<ES-S> Q39 " +
				"<ES-L> Q9 "
		);
		map.put(name, node);


		// Q9
		name = "Q9";
		node = new Node(name, 1);
		node.point("<SE-C> Q8 " +
				"<SE-SC> Q38 " +
				"<SE-SLC> Q37 " +
				"<SE-LC> Q42 " +
				"<SE-SL> Q39 " +
				"<SE-L> Q40 " +
				"<SM-S> Q41 " +
				"<SM-SL> Q44 " +
				"<SM-SC> Q43 " +
				"<SM-SLC> Q10 "
		);
		map.put(name, node);


		// Q41
		name = "Q41";
		node = new Node(name, 1);
		node.point("<MS-S> Q9 " +
				"<SM-L> Q44 " +
				"<SM-LC> Q10 " +
				"<SM-C> Q43 "
		);
		map.put(name, node);


		// Q44
		name = "Q44";
		node = new Node(name, 1);
		node.point("<MS-SL> Q9 " +
				"<MS-L> Q41 " +
				"<SM-C> Q10 "
		);
		map.put(name, node);


		// Q43
		name = "Q43";
		node = new Node(name, 1);
		node.point("<MS-C> Q41 " +
				"<MS-SC> Q9 " +
				"<SM-L> Q10 "
		);
		map.put(name, node);


		// Q10
		name = "Q10";
		node = new Node(name, 0);
		node.point("<MS-SLC> Q9 " +
				"<MS-C> Q44 " +
				"<MS-LC> Q41 " +
				"<MS-L> Q43 "
		);
		map.put(name, node);
	}

	public static Node get(String nodeName) {
		return map.get(nodeName).clone();
	}

	public static int check(String node1, String node2, String path) {
		return check(Reference.get(node1), Reference.get(node2), path);
	}

	public static int check(Node node1, Node node2, String path) {
		/* Check if node1 contains path */
		for (Path p : node1.paths()) {
			if (p.dest().equalsIgnoreCase(node2.name()) && p.path().equalsIgnoreCase(path))
				return 1;
		}

		/* Check if node2 contains path */
		for (Path p : node2.paths()) {
			if (p.dest().equalsIgnoreCase(node1.name()) && p.path().equalsIgnoreCase(path))
				return 2;
		}

		return 0;
	}

	public static String smartChoice(String state) {
		Node node = get(state);
		List<Node> nextNodes = new ArrayList<>();
		List<Integer> dists = new ArrayList<>();
		int iMin = -1;

		for (Path p : node.paths())
			nextNodes.add(Reference.get(p.dest()));
		for (Node n : nextNodes)
			dists.add(n.dist());

		for (int i = 0; i < dists.size(); i++) {
			if (i == 0) {
				iMin = 0;
				continue;
			}
			if (dists.get(i) < dists.get(iMin))
				iMin = i;
		}
		return node.paths().get(iMin).path();
	}

	public static String inString() {
		String state;
		String str = "";

		for (int i = 0; i < map.size(); i++) {
			state = "Q" + i;
			str += map.get(state) + "\n";
		}

		return str;
	}
}

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
			System.out.println("------------------");
			System.out.println("\tTurn [" +turn+ "]\t");
			System.out.println("------------------");
			showState(currentState);
			System.out.println("Input hint: <source> <destination> <entity1> <entity2> ... <entityN>");
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

		System.out.println("You solved the game!");
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

		input = "<" + toEnv(in_[0]) + toEnv(in_[1]);
		input += "-";

		for (int i = 2; i < in_.length; i++) {
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
				"\tInput format : \"<source> <destination> <entity1> <entity2> ... <entityN>\"";
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
