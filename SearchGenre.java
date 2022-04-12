package model;

import java.util.ArrayList;
import java.util.List;

public class SearchGenre implements SearchStrategy<Genre>{

	@Override
	public List<VideoGame> searchBy(List<VideoGame> oldVideoGames, Genre type) {
		List<VideoGame> newList = new ArrayList<VideoGame>();
		for (VideoGame vg : oldVideoGames) {
			if(vg.getGenre().getName().equals(type.getName())) {
				newList.add(vg);
			}
		}
		return newList;	
	}

}
