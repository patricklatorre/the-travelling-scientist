package automaton;

import java.util.ArrayList;
import java.util.HashMap;

class Node {
	private String name;
	private ArrayList<Path> paths; // <path, name>

	public Node(String name) {
		this.name = name;
		paths = new ArrayList<>();
	}

	public void point(String edges) {
		String[] properties = edges.split(" ");
		for (int i = 0; i < properties.length - 1; i = i + 2)
			paths.add(new Path(properties[i], properties[i + 1]));
	}

	public String name() {
		return name;
	}

	public ArrayList<Path> paths() {
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

	public static void initialize() {
		map = new HashMap<>();

		String name;
		Node node;


		// q0
		name = "Q0";
		node = new Node(name);
		node.point("<ES-C> Q20" +
				"<ES-SLC> Q1" +
				"<ES-L> Q21" +
				"<ES-LG> Q46" +
				"<ES-G> Q25" +
				"<ES-HHG> Q22" +
				"<ES-HH> Q23" +
				"<ES-H> Q45" +
				"<ES-HG> Q24"
		);
		map.put(name, node);


		// q20
		name = "Q20";
		node = new Node(name);
		node.point("<SE-C> Q0" +
				"<ES-SL> Q1"
		);
		map.put(name, node);


		// q21
		name = "Q21";
		node = new Node(name);
		node.point("<SE-L> Q0" +
				"<ES-G> Q46" +
				"<ES-SC> Q1"
		);
		map.put(name, node);

		// q46
		name = "Q46";
		node = new Node(name);
		node.point("<SE-LG> Q0" +
				"<ES-L> Q25" +
				"<SE-G> Q21"
		);
		map.put(name, node);

		// q25
		name = "Q25";
		node = new Node(name);
		node.point("<SE-G> Q0" +
				"<SE-L> Q46" +
				"<ES-HH> Q22" +
				"<ES-H> Q24"
		);
		map.put(name, node);

		// q22
		name = "Q22";
		node = new Node(name);
		node.point("<SE-G> Q23" +
				"<SE-HG> Q45" +
				"<SE-H> Q24" +
				"<SE-HHG> Q0" +
				"<SE-HH> Q25"
		);
		map.put(name, node);


		// q23
		name = "Q23";
		node = new Node(name);
		node.point("<SE-HH> Q0" +
				"<ES-G> Q22" +
				"<SE-H> Q45"
		);
		map.put(name, node);


		//
		name = "Q45";
		node = new Node(name);
		node.point("<ES-H> Q23" +
				"<ES-G> Q24" +
				"<SE-H> Q0" +
				"<ES-HG> Q22"
		);
		map.put(name, node);


		//
		name = "Q24";
		node = new Node(name);
		node.point("<SE-G> Q45" +
				"<ES-H> Q22" +
				"<SE-H> Q25" +
				"<SE-HG> Q0"
		);

		//
		name = "";
		node = new Node(name);
		node.point("" +
				"" +
				"" +
				"" +
				"" +
				"" +
				""
		);
		map.put(name, node);
	}
}
