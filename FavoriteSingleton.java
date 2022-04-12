package model;

import java.util.HashSet;
import java.util.Set;



public class FavoriteSingleton {

	private static FavoriteSingleton instance = new FavoriteSingleton();
	private Set<VideoGame> favoriteVideoGames = new HashSet<VideoGame>();

	private FavoriteSingleton() {}

	public static FavoriteSingleton getInstance() {
		return instance;
	}
	
	public int numberOfFavorite() {
		return favoriteVideoGames.size();
	}

	public Set<VideoGame> getFavoriteVideoGames() {
		return favoriteVideoGames;
	}

	public void setFavoriteVideoGame(VideoGame favoriteVideoGames) {
		this.favoriteVideoGames.add(favoriteVideoGames);
	}
	
	public void removeFavoriteVideoGame(VideoGame favoriteVideoGames) {
		this.favoriteVideoGames.remove(favoriteVideoGames);
	}

	@Override
	public String toString() {
		return "\nfavoriteVideoGames: " + favoriteVideoGames;
	}
	
	


}
