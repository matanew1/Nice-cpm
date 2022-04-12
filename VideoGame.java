package model;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class VideoGame {
	
	private String id;
	private String name;
	private Company publisherCompany;
	private Company developerCompany;
	private String yearCreation;
	private Genre genre;
	private Trailer trailer;
	private List<Platform> platforms;
	private String description;
	private double rating;
	private Button likeButton;
	private Button trailerButton;
	
	public VideoGame(String name) {
		this.id = "";
		this.setRating(0.0);
		this.name = name;
		this.publisherCompany = new PublisherCompany("","");
		this.developerCompany = new DeveloperCompany("","");
		this.genre = new Genre("");
		this.trailer = new Trailer("");
		this.platforms = new ArrayList<Platform>();
		this.description = "";
		this.yearCreation = "";
		this.likeButton = new  Button(" ");
		this.trailerButton = new Button("♣");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.likeButton.setId(id);		
		this.id = id;
	}
	
	public void buttonLikeEvent(EventHandler<ActionEvent> click) {
		this.likeButton.setOnAction(click);
	}

	public void setPublisherCompany(Company publisherCompany) {
		this.publisherCompany = publisherCompany;
	}

	public void setDeveloperCompany(Company developerCompany) {
		this.developerCompany = developerCompany;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getPublisherCompany() {
		return publisherCompany;
	}

	public Company getDeveloperCompany() {
		return developerCompany;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		
		this.genre = genre;
	}

	public Trailer getTrailer() {
		return trailer;
	}
	

	public Button getTrailerButton() {
		return trailerButton;
	}
	
	public void buttonTrailerEvent(EventHandler<ActionEvent> click) {
		this.trailerButton.setOnAction(click);
	}

	public void setTrailerButton(Button trailerButton) {
		this.trailerButton = trailerButton;
	}


	public void setTrailer(Trailer trailer) {
		this.trailerButton.setId(id);
		this.trailer = trailer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public void setYearCreation(String yearCreation) {
		this.yearCreation = yearCreation;
	}

	@Override
	public String toString() {
		return "\nID: "+id+"\nVideo Game Name: " + name +"\nPublisher: "+ publisherCompany +"\nDeveloper: "+ developerCompany +"\nGenre: "+  genre +"\nTrailer: "+  trailer 
				+"\nPlatforms: "+ platforms.toString() + "\nDescription: " + description + "\nRating: " + rating+"\nYear: "+yearCreation;
	}


	public String getYearCreation() {
		return yearCreation;
	}


	public void setReleaseYear(String year) {
		this.yearCreation = year;
	}


	public Button getLikeButton() {
		return likeButton;
	}


	public void setLikeButton(Button likeButton) {
		this.likeButton = likeButton;
	}
	
	

}
