package model;

import java.util.ArrayList;
import java.util.List;

public class Model<T> {
	public enum eTypeComboBox{eDefualt,eGenre,ePlatform,ePublisherCompany,eDeveloperCompany,eReleaseYear};

	private eTypeComboBox typesCombo;
	private List<VideoGame> vgList;
	private List<Genre> genList;
	private List<DeveloperCompany> devCompList;
	private List<PublisherCompany> pubCompList;
	private List<Platform> platList;
	private SearchEngineContext<T> searchEngine;
	private FavoriteSingleton favorites;
	private ConnectMySQL conSQL;

	public Model() {
		genList = new ArrayList<Genre>();
		vgList = new ArrayList<VideoGame>();
		vgList.sort((VideoGame v1, VideoGame v2) -> (v1.getName().compareTo(v2.getName())));
		devCompList = new ArrayList<DeveloperCompany>();
		pubCompList = new ArrayList<PublisherCompany>();
		platList = new ArrayList<Platform>();
		favorites = FavoriteSingleton.getInstance();
		typesCombo = eTypeComboBox.eDefualt;
		searchEngine = null;
		conSQL = new ConnectMySQL();
		conSQL.readAll(vgList,genList,platList,pubCompList,devCompList);
		
	}

	public List<VideoGame> getVgList() {
		return vgList;
	}

	public SearchEngineContext<T> getSearchEngine() {
		return searchEngine;
	}

	public void SearchBy(SearchStrategy<T> searchBy) {
		searchEngine = new SearchEngineContext<T>(searchBy);
	}

	public FavoriteSingleton getFavorites() {
		return favorites;
	}

	public List<Genre> getGenList() {
		return genList;
	}

	public List<DeveloperCompany> getDevCompList() {
		return devCompList;
	}

	public List<PublisherCompany> getPubCompList() {
		return pubCompList;
	}

	public List<Platform> getPlatList() {
		return platList;
	}

	public ConnectMySQL getConSQL() {
		return conSQL;
	}

	public eTypeComboBox getTypesCombo() {
		return typesCombo;
	}

	public void setTypesCombo(eTypeComboBox typesCombo) {
		this.typesCombo = typesCombo;
	}


}
