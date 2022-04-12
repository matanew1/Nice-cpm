package model;

import java.util.ArrayList;
import java.util.List;

public class SearchName implements SearchStrategy<String>{

	@Override
	public List<VideoGame> searchBy(List<VideoGame> oldVideoGames, String type) {
		List<VideoGame> newList = new ArrayList<VideoGame>();
		for (VideoGame vg : oldVideoGames) {
			if(vg.getName().contains(type.toLowerCase()) || vg.getName().contains(type.toUpperCase())) {
				newList.add(vg);
			}
		}
		return newList;				
	}

}
