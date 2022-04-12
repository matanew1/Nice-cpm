package model;

public abstract class Company {
	private String name;
	private String id ; 
	
	public Company(String name,String id) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
