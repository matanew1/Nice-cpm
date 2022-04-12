package model;

import java.util.ArrayList;
import java.util.List;

public class SearchDeveloperCompany implements SearchStrategy<DeveloperCompany>{

	@Override
	public List<VideoGame> searchBy(List<VideoGame> oldVideoGames, DeveloperCompany type) {
		List<VideoGame> newList = new ArrayList<VideoGame>();
		for (VideoGame vg : oldVideoGames) {
			if(vg.getDeveloperCompany().getName().equals(type.getName())) {
				newList.add(vg);
			}
		}
		return newList;	
	}

}
