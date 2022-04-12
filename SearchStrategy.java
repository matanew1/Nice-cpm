package model;

import java.util.List;

public interface SearchStrategy<T> {
	List<VideoGame> searchBy(List<VideoGame> oldVideoGames,T type);
}
 