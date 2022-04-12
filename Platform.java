package model;

public class Platform {
	
	private String name;
	private String id;

	public Platform(String name,String id) {
		this.name = name;
		this.setId(id);
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
