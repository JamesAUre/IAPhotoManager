package IA;

public class Photo {
	int id;
	String path;
	String name;
	
	public Photo(int id, String path, String name){
		this.id = id;
		this.path = path;
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
}
