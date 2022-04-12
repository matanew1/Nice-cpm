package model;

import java.util.ArrayList;
import java.util.List;

public class SearchYear implements SearchStrategy<String>{

	@Override
	public List<VideoGame> searchBy(List<VideoGame> oldVideoGames, String type) {
		List<VideoGame> newList = new ArrayList<VideoGame>();
		for (VideoGame vg : oldVideoGames) {
			if(vg.getYearCreation().equals(type)) {
				newList.add(vg);
			}
		}
		return newList;	
	}

}
