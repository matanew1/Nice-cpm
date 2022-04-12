package model;

import java.util.List;

public class SearchEngineContext<T>{
	
	   private SearchStrategy<T> search;

	   public SearchEngineContext(SearchStrategy<T> search){
	      this.search = search;
	   }

	   public List<VideoGame> executeSearch(List<VideoGame> videoGames, T type){
	      return search.searchBy(videoGames, type);
	   }

}
