package graphEngine;

import java.util.ArrayList;
import java.util.List;

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

	public String toTabbedString(String tabs) {
		String str = "";
		str += tabs + name + ":\n";
		for (Path p : paths)
			str += tabs + "\t" + p.toString() + "\n";
		return str;
	}

	@Override
	public Node clone() {
		return new Node(this);
	}
}
