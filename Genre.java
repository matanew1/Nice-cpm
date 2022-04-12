package model;

public class Genre {
	
	private String id;
	private String name;

	public Genre(String name) {
		this.name = name;
	}	

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
