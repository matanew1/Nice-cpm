package model;

public class Trailer {
	
	private String url;

	public Trailer(String link) {
		this.setUrl(link);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getText() {
		return url;
	}

	@Override
	public String toString() {
		return url;
	}
	
	


}
