package model;

public class Description {

	private String url;
	
	public Description(String link) {
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
