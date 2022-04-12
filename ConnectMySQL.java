package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Model.eTypeComboBox;

public class ConnectMySQL {

	private Connection con;
	private String url;
	private String username;
	private String password;
	private Statement statement;
	private ResultSet result;
	private PreparedStatement ps;

	public ConnectMySQL() {

		url = "jdbc:mysql://localhost:3306/video_game";
		username = "root";
		password = "Mb2702";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			System.out.println("Opps");
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public void readAll(List<VideoGame> vgList, List<Genre> genList, List<Platform> platList,
			List<PublisherCompany> pubCompList, List<DeveloperCompany> devCompList) {
		readGames(vgList);
		readGenres(genList);
		readPlatforms(platList);
		readPublishers(pubCompList);
		readDevelopers(devCompList);
	}

	public void readDevelopers(List<DeveloperCompany> list) {
		String get_all_developers = "select * from developer;";
		try {
			statement = con.createStatement();

			result = statement.executeQuery(get_all_developers);
			while (result.next()) {
				DeveloperCompany dev = null;
				String name = result.getString("developer_name");
				String id = result.getString("id");
				dev = new DeveloperCompany(name,id);
				list.add(dev);
			}
		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL exception:" + "," + ex.getMessage());
				ex = ex.getNextException();
			}

		}
	}

	public void readPublishers(List<PublisherCompany> list) {
		String get_all_publishers = "select * from publisher;";
		try {
			statement = con.createStatement();

			result = statement.executeQuery(get_all_publishers);
			while (result.next()) {
				PublisherCompany pub = null;
				String name = result.getString("publisher_name");
				String id = result.getString("id");
				pub = new PublisherCompany(name, id);
				list.add(pub);
			}
		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL exception:" + "," + ex.getMessage());
				ex = ex.getNextException();
			}

		}
	}

	public void readPlatforms(List<Platform> list) {
		String get_all_platforms = "select * from platform;";
		try {
			statement = con.createStatement();

			result = statement.executeQuery(get_all_platforms);
			while (result.next()) {
				Platform platform = null;
				String name = result.getString("platform_name");
				String id = result.getString("id");
				platform = new Platform(name, id);
				list.add(platform);
			}
		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL exception:" + "," + ex.getMessage());
				ex = ex.getNextException();
			}

		}
	}

	public void readGenres(List<Genre> list) {
		String get_all_genre = "select * from genre;";
		try {
			statement = con.createStatement();

			result = statement.executeQuery(get_all_genre);
			while (result.next()) {
				Genre genre = null;
				String name = result.getString("genre_name");
				genre = new Genre(name);
				list.add(genre);
			}
		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL exception:" + "," + ex.getMessage());
				ex = ex.getNextException();
			}

		}
	}

	public void readGames(List<VideoGame> list) {

		String get_all = "select * "
				+ " from game join game_genre join genre join game_publisher join publisher join game_developer join developer "
				+ " on (game.id = game_genre.game_id)  and (game_genre.genre_id = genre.id) and (game_publisher.game_id = game.id) "
				+ " and ( game_publisher.publisher_id = publisher.id) and (game.id = game_developer.game_id) and (game_developer.developer_id = developer.id)"
				+ " group by game.id order by game_name;";

		String get_platfrom = "select * " + " from game join game_platform join platform "
				+ " on (game.id = game_platform.game_id) and (game_platform.platform_id = platform.id) "
				+ " order by game_name;";
		try {
			statement = con.createStatement();
			result = statement.executeQuery(get_all);
			while (result.next()) {
				VideoGame videoGame = new VideoGame("");
				int temp = result.getInt("game.id");
				String id = Integer.toString(temp);
				String name = result.getString("game_name");
				Genre g = new Genre(result.getString("genre_name"));
				PublisherCompany pc = new PublisherCompany(result.getString("publisher_name"),result.getString("publisher.id"));
				DeveloperCompany dc = new DeveloperCompany(result.getString("developer_name"),result.getString("developer.id"));
				Trailer trailer = new Trailer(result.getString("trailer"));
				String desc = result.getString("game_description");
				String year = result.getString("year_release");
				float rating = result.getFloat("game_rating");

				Statement statement1 = con.createStatement();
				ResultSet result2 = statement1.executeQuery(get_platfrom);
				List<Platform> lp = new ArrayList<Platform>();
				String current = name;
				while (result2.next()) {
					if (result2.getString("game_name").equals(current)) {
						current = name;
						lp.add(new Platform(result2.getString("platform_name"),result2.getString("platform.id")));
					}
				}
				videoGame.setPlatforms(lp);
				videoGame.setId(id);
				videoGame.setName(name);
				videoGame.setTrailer(trailer);
				videoGame.setReleaseYear(year);
				videoGame.setGenre(g);
				videoGame.setDeveloperCompany(dc);
				videoGame.setPublisherCompany(pc);
				videoGame.setDescription(desc);
				videoGame.setRating(rating);
				list.add(videoGame);
			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL exception:" + "," + ex.getMessage());
				ex = ex.getNextException();
			}

		}
	}

	public void closeConnection() {
		try {
			statement.close();
			result.close();
			con.close();
		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL exception:" + "," + ex.getMessage());
				ex = ex.getNextException();
			}
		}

	}

//	public <T> void add(T type) {
//		if (type instanceof VideoGame) {
//			VideoGame vg = (VideoGame) type;
//			String query = "insert into game values "+"(null,"+vg.getName()+","+vg.getYearCreation()+","+vg.getTrailer()+","+vg.getDescription()+
//					","+String.valueOf(vg.getRating())+");";
//		} else if (type instanceof Genre) {
//
//		} else if (type instanceof PublisherCompany) {
//
//		} else if (type instanceof DeveloperCompany) {
//
//		} else if (type instanceof Platform) {
//
//		} else {
//			System.out.println("Error !");
//		}
//	}

	public <T> void remove(T type) {
		String query = null;
		if (type instanceof VideoGame) {
			VideoGame vg = (VideoGame) type;
			query = "delete from game_platform where game_platform.game_id = " + vg.getId() + "; "
					+ " delete from game_genre where game_genre.game_id = " + vg.getId() + "; "
					+ " delete from game_publisher where game_publisher.game_id = " + vg.getId() + "; "
					+ " delete from game_developer where game_developer.game_id = " + vg.getId() + "; "
					+ " delete from game where game.id = " + vg.getId() + "; ";
		} else if (type instanceof Genre) {
			Genre g = (Genre) type;
			query = "delete from game_genre where game_genre.genre_id = " + g.getId() + "; "
					+ " delete from genre where genre.id = " + g.getId() + "; ";
		} else if (type instanceof PublisherCompany) {
			PublisherCompany pc = (PublisherCompany) type;
			query = "delete from game_publisher where game_publisher.publisher_id = " + pc.getId() + "; "
					+ " delete from publisher where publisher.id = " + pc.getId() + "; ";
		} else if (type instanceof DeveloperCompany) {
			DeveloperCompany dc = (DeveloperCompany) type;
			query = "delete from game_developer where game_developer.developer_id = " + dc.getId() + "; "
					+ " delete from developer where developer.id = " + dc.getId() + "; ";
		} else if (type instanceof Platform) {
			Platform p = (Platform) type;
			query = "delete from game_platform where game_platform.platform_id = " + p.getId() + "; "
					+ " delete from platform where platform.id = " + p.getId() + "; ";

		} else {
			System.out.println("Error !");
		}
		try {
			ps = con.prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
