package IA;

public class User{
	int id;
	String profilepic;
	String username;
	String password;
	
	public User(int id, String profilepic, String username, String password){
		this.id = id;
		this.profilepic = profilepic;
		this.username = username;
		this.password = password;
	}

	public User() {
		this.id = -1;
		this.profilepic = "";
		this.password = "";
		this.username = "";
	}
	public String getProfilepic() {
		return profilepic;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String tags) {
		this.password = tags;
	}
	public void setProfilepic(String path) {
		this.profilepic = path;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public int getId() {
		return id;
	}

	public String getFileName() {
		return getProfilepic().substring(getProfilepic().lastIndexOf("\\") +1);
	}
	public String getFileNameExtension() {
		return getFileName().substring(getFileName().lastIndexOf("."));
	}
}
