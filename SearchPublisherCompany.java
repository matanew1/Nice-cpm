package model;

import java.util.ArrayList;
import java.util.List;

public class SearchPublisherCompany implements SearchStrategy<PublisherCompany>{

	@Override
	public List<VideoGame> searchBy(List<VideoGame> oldVideoGames, PublisherCompany type) {
		List<VideoGame> newList = new ArrayList<VideoGame>();
		for (VideoGame vg : oldVideoGames) {
			if(vg.getPublisherCompany().getName().equals(type.getName())) {
				newList.add(vg);
			}
		}
		return newList;	
	}

}
