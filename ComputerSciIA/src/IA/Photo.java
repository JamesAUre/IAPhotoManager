package IA;

public class Photo{
	int id;
	String path;
	String name;
	String tags;
	
	public Photo(int id, String path, String name, String tags){
		this.id = id;
		this.path = path;
		this.name = name;
		this.tags = tags;
	}

	public String getPath() {
		return path;
	}

	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
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

	public String getFileName() {
		return getPath().substring(getPath().lastIndexOf("\\") +1);
	}
	public String getFileNameExtension() {
		return getFileName().substring(getFileName().lastIndexOf("."));
	}
}
