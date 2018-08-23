package graphEngine;

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
