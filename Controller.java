package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.DeveloperCompany;
import model.Genre;
import model.Model;
import model.Platform;
import model.PublisherCompany;
import model.SearchDeveloperCompany;
import model.Model.eTypeComboBox;
import model.SearchEngineContext;
import model.SearchGenre;
import model.SearchName;
import model.SearchPlatform;
import model.SearchPublisherCompany;
import model.SearchStrategy;
import model.SearchYear;
import model.VideoGame;

public class Controller<T>{

	private Model<T> model = new Model<T>();
	private eTypeComboBox eTypeCurrentListComboBox = eTypeComboBox.eDefualt;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button btnExeComp;

	@FXML
	private Button btnEnterTextFieldSearch;

	@FXML
	private Button btnFavorite;

	@FXML
	private Button btnGenre;

	@FXML
	private Button btnHome;

	@FXML
	private Button btnName;

	@FXML
	private Button btnPlatform;

	@FXML
	private Button btnPubComp;

	@FXML
	private Button btnTopTen;

	@FXML
	private Button btnYear;

	@FXML
	private Button exitBtn;

	@FXML
	private Label lblMain;

	@FXML
	private TableView<VideoGame> tableList;

	@FXML
	private TableColumn<VideoGame, String> description;

	@FXML
	private TableColumn<VideoGame, String> devComp;

	@FXML
	private TableColumn<VideoGame, Button> like;

	@FXML
	private TableColumn<VideoGame, String> name;

	@FXML
	private TableColumn<VideoGame, String> platform;

	@FXML
	private TableColumn<VideoGame, String> pubComp;

	@FXML
	private TableColumn<VideoGame, Double> rating;

	@FXML
	private TableColumn<VideoGame, String> genre;

	@FXML
	private TableColumn<VideoGame, Button> trailer;

	@FXML
	private TableColumn<VideoGame, String> year;

	@FXML
	private ComboBox<String> mainComboList;

	@FXML
	private Text txtCombo;

	@FXML
	private TextField txtFieldName;

	@FXML
	private Text txtSearchName;
	


	javafx.event.EventHandler<ActionEvent> eventLike = new javafx.event.EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() instanceof Button) {
				Button b = (Button) event.getSource();
				for (int i = 0; i < model.getVgList().size(); i++) {
					if (model.getVgList().get(i).getLikeButton().getId().equals(b.getId())) {
						if (b.getText().equals(" ")) {
							b.setText("♥");
							b.setFont(new Font(14));
							b.setTextFill(Color.RED);
							model.getFavorites().setFavoriteVideoGame(model.getVgList().get(i));
						} else {
							b.setText(" ");
							model.getFavorites().removeFavoriteVideoGame(model.getVgList().get(i));
						}

					}
				}

			}

		}
	};

	javafx.event.EventHandler<ActionEvent> eventTrailer = new javafx.event.EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {

			if (event.getSource() instanceof Button) {

				Button b = (Button) event.getSource();
				for (int i = 0; i < model.getVgList().size(); i++) {
					if (model.getVgList().get(i).getTrailerButton().getId().equals(b.getId())) {
						showTrailer(model.getVgList().get(i).getTrailer().getText());
					}
				}

			}

		}

		private void showTrailer(String url) {
			Stage stage = new Stage();
			AnchorPane ap = new AnchorPane();
			VBox v = new VBox();
			WebView w = new WebView();
			w.getEngine().load(url);
			v.getChildren().add(w);
			ap.getChildren().addAll(v);
			Scene scene = new Scene(ap, 800, 800);
			stage.setScene(scene);
			stage.show();
		}
	};

	public void initialize(List<VideoGame> filteredList) {
		name.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("name"));
		genre.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("genre"));
		platform.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("platforms"));
		pubComp.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("publisherCompany"));
		devComp.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("developerCompany"));
		year.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("yearCreation"));
		trailer.setCellValueFactory(new PropertyValueFactory<VideoGame, Button>("trailerButton"));
		rating.setCellValueFactory(new PropertyValueFactory<VideoGame, Double>("rating"));
		description.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("description"));
		like.setCellValueFactory(new PropertyValueFactory<VideoGame, Button>("likeButton"));
		tableList.setItems(FXCollections.observableArrayList(filteredList));
		for (int i = 0; i < filteredList.size(); i++) {
			filteredList.get(i).buttonLikeEvent(eventLike);
			filteredList.get(i).buttonTrailerEvent(eventTrailer);
		}

	}

	@FXML
	void homeButtonClicked(ActionEvent event) {
		visibleData(false);
		btnHome = new Button();
		String s = "Our Games";
		lblMain.setText(s);
		visibleData(false);
		tableList.setItems(null);
	}

	@FXML
	void exeButtonClicked(ActionEvent event) {
		eTypeCurrentListComboBox = eTypeComboBox.eDeveloperCompany;
		visibleComboData(false);
		btnExeComp = new Button();
		String s = "Search By Developer\n        Company";
		lblMain.setText(s);

		txtCombo.setText("Select a Developer Company :");
		Set<String> stringList = new TreeSet<String>();

		for (int i = 0; i < model.getDevCompList().size(); i++) {
			stringList.add(model.getDevCompList().get(i).getName());
		}
		initializeComboBox(stringList, eTypeComboBox.eDeveloperCompany);

	}

	@FXML
	void favoriteButtonClicked(ActionEvent event) {
		visibleData(false);
		btnFavorite = new Button();
		String s = "My Favorite";
		lblMain.setText(s);
		List<VideoGame> list = List.copyOf(model.getFavorites().getFavoriteVideoGames());
		initialize(list);

	}

	@FXML
	void genreButtonClicked(ActionEvent event) {
		eTypeCurrentListComboBox = eTypeComboBox.eGenre;
		visibleComboData(false);
		btnGenre = new Button();
		String s = "Search By Genre";
		lblMain.setText(s);

		txtCombo.setText("Select a genre :");
		Set<String> stringList = new TreeSet<String>();

		for (int i = 0; i < model.getGenList().size(); i++) {
			stringList.add(model.getGenList().get(i).getName());
		}
		initializeComboBox(stringList, eTypeComboBox.eGenre);
	}

	@FXML
	void nameButtonClicked(ActionEvent event) {
		visibleTextFieldData(false);
		btnName = new Button();
		String s = "Search By Name";
		lblMain.setText(s);
	}

	@FXML
	void platformButtonClicked(ActionEvent event) {
		eTypeCurrentListComboBox = eTypeComboBox.ePlatform;
		visibleComboData(false);
		btnPlatform = new Button();
		String s = "Search By Platform";
		lblMain.setText(s);
		Set<String> stringList = new TreeSet<String>();
		for (Platform p : model.getPlatList()) {
			stringList.add(p.getName());
		}

		initializeComboBox(stringList, eTypeComboBox.ePlatform);
	}

	@FXML
	void pubButtonClicked(ActionEvent event) {
		eTypeCurrentListComboBox = eTypeComboBox.ePublisherCompany;
		visibleComboData(false);
		btnPubComp = new Button();
		String s = "Search By Publisher\n        Company";
		lblMain.setText(s);

		txtCombo.setText("Select a Publisher Company :");
		Set<String> stringList = new TreeSet<String>();

		for (int i = 0; i < model.getPubCompList().size(); i++) {
			stringList.add(model.getPubCompList().get(i).getName());
		}
		initializeComboBox(stringList, eTypeComboBox.ePublisherCompany);

	}

	@FXML
	void topTenButtonClicked(ActionEvent event) {
		visibleData(false);
		btnTopTen = new Button();
		String s = "Top 10\n        Video Games";
		lblMain.setText(s);

	}

	@FXML
	void yearButtonClicked(ActionEvent event) {
		eTypeCurrentListComboBox = eTypeComboBox.eReleaseYear;
		visibleComboData(false);
		btnYear = new Button();
		String s = "Search By\n        Release Year";
		lblMain.setText(s);

		txtCombo.setText("Select a Release Year :");
		Set<String> stringList = new TreeSet<String>();
		for (int i = 0; i < model.getVgList().size(); i++) {
			stringList.add(model.getVgList().get(i).getYearCreation());
		}
		initializeComboBox(stringList, eTypeComboBox.eReleaseYear);
	}

	@FXML
	void pressedExitButton(ActionEvent event) {
		model.getConSQL().closeConnection();
		Stage stage = (Stage) anchorPane.getScene().getWindow();
		stage.close();
	}

	private void visibleComboData(boolean b) {
		txtCombo.setVisible(!b);
		mainComboList.setVisible(!b);
		txtFieldName.setVisible(b);
		txtSearchName.setVisible(b);
		btnEnterTextFieldSearch.setVisible(b);
	}

	private void visibleData(boolean b) {
		txtCombo.setVisible(b);
		mainComboList.setVisible(b);
		txtFieldName.setVisible(b);
		txtSearchName.setVisible(b);
		btnEnterTextFieldSearch.setVisible(b);
	}

	private void visibleTextFieldData(boolean b) {
		txtCombo.setVisible(b);
		mainComboList.setVisible(b);
		txtFieldName.setVisible(!b);
		txtSearchName.setVisible(!b);
		btnEnterTextFieldSearch.setVisible(!b);
	}

	public Model<T> getModel() {
		return model;
	}

	@FXML
	void selectInputFromComboBox(ActionEvent event) {

		switch (eTypeCurrentListComboBox) {
		case eGenre:

			SearchStrategy<Genre> searchG = new SearchGenre();
			SearchEngineContext<Genre> engine1 = new SearchEngineContext<Genre>(searchG);
			List<VideoGame> filterVideoGames1 = new ArrayList<VideoGame>();
			filterVideoGames1 = engine1.executeSearch(model.getVgList(), new Genre(mainComboList.getValue()));
			if (!filterVideoGames1.isEmpty())
				initialize(filterVideoGames1);
			break;
		case ePlatform:

			SearchStrategy<Platform> searchP = new SearchPlatform();
			SearchEngineContext<Platform> engine2 = new SearchEngineContext<Platform>(searchP);
			List<VideoGame> filterVideoGames2 = new ArrayList<VideoGame>();
			filterVideoGames2 = engine2.executeSearch(model.getVgList(), new Platform(mainComboList.getValue(), null));
			if (!filterVideoGames2.isEmpty())
				initialize(filterVideoGames2);
			break;

		case eReleaseYear:
			SearchStrategy<String> searchY = new SearchYear();
			SearchEngineContext<String> engine3 = new SearchEngineContext<String>(searchY);
			List<VideoGame> filterVideoGames3 = new ArrayList<VideoGame>();
			filterVideoGames3 = engine3.executeSearch(model.getVgList(), mainComboList.getValue());
			if (!filterVideoGames3.isEmpty())
				initialize(filterVideoGames3);
			break;

		case eDeveloperCompany:
			SearchStrategy<DeveloperCompany> searchDC = new SearchDeveloperCompany();
			SearchEngineContext<DeveloperCompany> engine4 = new SearchEngineContext<DeveloperCompany>(searchDC);
			List<VideoGame> filterVideoGames4 = new ArrayList<VideoGame>();
			filterVideoGames4 = engine4.executeSearch(model.getVgList(),
					new DeveloperCompany(mainComboList.getValue(), null));
			if (!filterVideoGames4.isEmpty())
				initialize(filterVideoGames4);
			break;

		case ePublisherCompany:
			SearchStrategy<PublisherCompany> searchPC = new SearchPublisherCompany();
			SearchEngineContext<PublisherCompany> engine5 = new SearchEngineContext<PublisherCompany>(searchPC);
			List<VideoGame> filterVideoGames5 = new ArrayList<VideoGame>();
			filterVideoGames5 = engine5.executeSearch(model.getVgList(),
					new PublisherCompany(mainComboList.getValue(), null));
			if (!filterVideoGames5.isEmpty())
				initialize(filterVideoGames5);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + eTypeCurrentListComboBox);
		}
	}

	@FXML
	void btnEnterSearch(ActionEvent event) {
		SearchStrategy<String> search = new SearchName();
		SearchEngineContext<String> engine = new SearchEngineContext<String>(search);
		List<VideoGame> filterVideoGames = new ArrayList<VideoGame>();
		filterVideoGames = engine.executeSearch(model.getVgList(), txtFieldName.getText());
		initialize(filterVideoGames);
	}

	public void initializeComboBox(Set<String> inputList, eTypeComboBox eType) {
		ObservableList<String> list = FXCollections.observableArrayList(inputList);
		mainComboList.setItems(list);
	}


}
