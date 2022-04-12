package model;

import java.util.ArrayList;
import java.util.List;

public class SearchPlatform implements SearchStrategy<Platform>{

	@Override
	public List<VideoGame> searchBy(List<VideoGame> oldVideoGames, Platform type) {
		List<VideoGame> newList = new ArrayList<VideoGame>();
		for (VideoGame vg : oldVideoGames) {
			for (Platform platform : vg.getPlatforms()) {
				if(platform.getName().equals(type.getName())) {
					newList.add(vg);
				}
			}
			
		}
		return newList;	
	}

}
