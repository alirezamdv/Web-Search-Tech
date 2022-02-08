import java.lang.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.sql.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.*;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Searcher {
	private static ArrayList<Arguments> arguments = new ArrayList<>();
	private static ArrayList<Argument> argument = new ArrayList<>();
	private static HashMap<String, Double> itemsAndDistances = new HashMap<>();
	private static HashMap<String, String> itemsAndPrices = new HashMap<>();
	private static HashMap<String, String> itemsAndNames = new HashMap<>();
	private static boolean hasLatitudeAndLongitude = false;
	private static String searchQuery;
	private static String longitude;
	private static String latitude;
	private static Double width;


    public Searcher() {}

	public static void main(String[] args) throws Exception {
		String usage = "java Searcher \"SearchQuery\" [-x longitude -y latitude -w width]";

		if (args.length != 1 && args.length != 7) {
			System.out.println("Usage: " + usage);
			System.exit(1);
		}
		getPriceFromDb(DbManager.getConnection(true));
		getNameFromDb(DbManager.getConnection(true));
		searchQuery = args[0];

		if (args.length == 7) {
			longitude = args[2];
			latitude = args[4];
			width = Double.parseDouble(args[6]);
			hasLatitudeAndLongitude = true;
			getDistanceFromGeoTable(DbManager.getConnection(true));
		}
		searchIndex();
	}

	private static void getNameFromDb(Connection connection) throws SQLException {
		Statement stmt = null;
		String query = "SELECT item_id, item_name \n" +
				"FROM item;";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String item_id = rs.getString("item_id");
				String item_name = rs.getString("item_name");
				itemsAndNames.put(item_id, item_name);
			}

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	private static void getPriceFromDb(Connection connection) throws SQLException {
		Statement stmt = null;
		String query = "SELECT item_id, current_price\n" +
				"FROM auction;";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String auction_itemID = rs.getString("item_id");
				String auction_current_price = rs.getString("current_price");
				itemsAndPrices.put(auction_itemID, auction_current_price);
			}

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public static void getDistanceFromGeoTable(Connection connection) throws SQLException {
		Statement stmt = null;
		String query = "SELECT itemID, \n" +
				"                ((ACOS(SIN(" + latitude + "* PI() / 180) \n" +
				"                * SIN(X(Location) * PI() / 180) + COS(" + latitude + "* PI() / 180)  \n" +
				"                * COS(X(Location) * PI() / 180)  \n" +
				"                * COS((" + longitude + " - (Y(Location))) * PI() / 180)) * 180 / PI()) * 60 * 1.1515 * 1.609344) \n" +
				"                AS distance \n" +
				"                FROM geo \n" +
				"                WHERE MBRContains \n" +
				"                    ( \n" +
				"                    LineString \n" +
				"                        ( \n" +
				"                        Point ( \n" +
				"                              " + latitude + " + " + (width * 1000) + "/ (111.320 * COS(RADIANS(" + longitude + " ))), +\n" +
				"                             + " + longitude + " + " + (width * 1000) + "/ 111.133\n" +
				"                             ),\n" +
				"                        Point ( \n" +
				"                              " + latitude + "  - " + (width * 1000) + "/ (111.320 * COS(RADIANS(" + longitude + "))), \n" +
				"                              " + longitude + " - " + (width * 1000) + "/ 111.133 \n" +
				"                        ) \n" +
				"                    ), \n" +
				"                    location \n" +
				"                    )HAVING distance < " + width + ";";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String geo_itemID = rs.getString("geo.itemID");
				String geo_distance = rs.getString("distance");
				itemsAndDistances.put(geo_itemID, Double.parseDouble(geo_distance));
			}
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}


	public static void searchIndex() throws Exception {
		Path path = Paths.get("indexes");
		Directory directory = FSDirectory.open(path);
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);

		QueryParser queryParser = new QueryParser("name_category_description", new SimpleAnalyzer());
		Query query = queryParser.parse(searchQuery);
		TopDocs docs = searcher.search(query, 10000);


		// for three decimal places
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.DOWN);


		int i = 0;
		for (ScoreDoc scoreDoc : docs.scoreDocs) {
			Document d = searcher.doc(scoreDoc.doc);
			if (hasLatitudeAndLongitude) {
				if (itemsAndDistances.containsKey(d.get("item_id"))) {
					arguments.add(
							new Arguments(
									d.get("item_id"),
									itemsAndNames.get(d.get("item_id")),
									String.valueOf(scoreDoc.score),
									df.format(itemsAndDistances.get(d.get("item_id"))),
									Double.parseDouble(itemsAndPrices.get(d.get("item_id")))
							));
					i++;
				}
			} else {
				argument.add(new Argument(
						d.get("item_id"),
						itemsAndNames.get(d.get("item_id")),
						String.valueOf(scoreDoc.score),
						Double.parseDouble(itemsAndPrices.get(d.get("item_id")))
				));
				i++;
			}
		}
		System.out.println("Running search(" + searchQuery + ")");
		if (hasLatitudeAndLongitude) {
			System.out.println("totalHits " + arguments.size());
			// comparator to compare first score, and then distance, and at last price
			Collections.sort(arguments, new Comparator<Arguments>() {
				@Override
				public int compare(Arguments o1, Arguments o2) {
					int c = o2.getScore().compareTo(o1.getScore());
					if (c == 0) {
						c = o1.getDist().compareTo(o2.getDist());
					}
					if (c == 0) {
						c = o1.getPrice().compareTo(o2.getPrice());
					}
					return c;
				}
			});
			for (Arguments Arguments : arguments) {
				System.out.println(Arguments);
			}
		} else {
			System.out.println("totalHits " + argument.size());
			// comparator to compare first score, and then price
			Collections.sort(argument, new Comparator<Argument>() {
				@Override
				public int compare(Argument o1, Argument o2) {
					int c = o2.getScore().compareTo(o1.getScore());
					if (c != 0) {
						return c;
					} else {
						return o1.getPrice().compareTo(o2.getPrice());
					}
				}
			});
			for (Argument Argument : argument) {
				System.out.println(Argument);
			}
		}
	}
}
