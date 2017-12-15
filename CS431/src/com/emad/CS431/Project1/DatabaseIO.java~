package com.emad.CS431.Project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseIO {
	private static String username = "sk972";
	private static String password = "v5OBKFwD0";
	private static String dbType = "mysql", dbName = "sk972";
	private static String host = "sql2.njit.edu";// ticket # 436169
	private static int port = 3306;
	private final static String URL = String.format("jdbc:%s://%s:%d/%s", dbType, host, port, dbName);

	private static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, username, password);
			// System.out.println("Connected!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	protected static void initIntoDigBooks() {
		ArrayList<String> authors = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/DigitalBooks/authors");
		ArrayList<String> titles = FileIO
				.getFileContent("src/com/emad/CS431/Project1/Resources/DigitalBooks/book_titles");
		ArrayList<String> isbns = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/DigitalBooks/isbn");
		Connection conn = connect();
		String query = "INSERT INTO Books_HardCopy VALUES (?,?,?,?,?)";

		try {
			for (int i = 0; i < 100; i++) {
				int qty = (int) (Math.random() * 200);
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);

				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, isbns.get(i));
				stmt.setInt(2, qty);
				stmt.setString(3, titles.get(i));
				stmt.setString(4, authors.get(i));
				stmt.setDouble(5, Double.parseDouble(df.format(25 + Math.random() * 350.00)));

				stmt.execute();
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	protected static void initIntoDigMovies() {
		ArrayList<String> titles = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/DigitalMovies/titles");
		ArrayList<String> year = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/DigitalMovies/year");
		Connection conn = connect();
		String query = "INSERT INTO Movies_DigitalDownload(Title, Quantity, Cost, Year) VALUES(?,?,?,?)";

		try {
			for (int i = 0; i < 100; i++) {
//				int qty = (int) (Math.random() * 200);
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);

				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, titles.get(i));
				stmt.setString(2, "N/A");
				stmt.setDouble(3, Double.parseDouble(df.format(25 + Math.random() * 350.00)));
				stmt.setInt(4, Integer.parseInt(year.get(i)));

				stmt.execute();
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	protected static ObservableList<AllTypes> getAll() {

		ObservableList<AllTypes> a = FXCollections.observableArrayList();
		ObservableList<Book> digBooks = getDigitalBooks();
		ObservableList<Book> hardBooks = getHardBooks();
		ObservableList<Movie> digMovies = getDigitalMovies();
		ObservableList<Movie> hardMovies = getHardMovies();
		for (int i = 0; i < digBooks.size(); i++) {
			a.add(new AllTypes("Digital Book", digBooks.get(i).getTitle(), digBooks.get(i).getQty(),
					digBooks.get(i).getCost()));

		}
		for (int i = 0; i < hardBooks.size(); i++) {
			a.add(new AllTypes("Hard Book", hardBooks.get(i).getTitle(), hardBooks.get(i).getQty(),
					hardBooks.get(i).getCost()));

		}
		for (int i = 0; i < digMovies.size(); i++) {
			a.add(new AllTypes("Digital Movie", digMovies.get(i).getTitle(), digMovies.get(i).getQty(),
					digMovies.get(i).getCost()));

		}
		for (int i = 0; i < hardMovies.size(); i++) {
			a.add(new AllTypes("Hard Movie", hardMovies.get(i).getTitle(), hardMovies.get(i).getQty(),
					hardMovies.get(i).getCost()));

		}
		return a;
	}

	// = "SELECT * from Books_DigitalDownload, Books_HardCopy,
	// Movies_DigitalDownload, Movies_Hardcopy WHERE
	// Books_DigitalDownload.title, Books_HardCopy.title, "
	// + "Movies_DigitalDownload.title, Movies_Hardcopy.title = ORDER BY Title"

	protected static ObservableList<Book> getDigitalBooks() {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_DigitalDownload ORDER BY Title";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Digital Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> getDigitalBooksCostBetween(double low, double high) {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_DigitalDownload WHERE cost BETWEEN '" + low + "' AND '" + high
				+ "' ORDER BY cost";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Digital Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> getHardBooks() {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_HardCopy ORDER BY Title";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Hard Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Movie> getDigitalMovies() {
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_DigitalDownload ORDER BY Title";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Digital Movie"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Movie> getHardMovies() {
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_Hardcopy ORDER BY Title";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Hard Movie"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> searchDigBookTitle(String s) {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_DigitalDownload WHERE title LIKE '%" + s + "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Digital Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> searchDigBookISBN(String s) {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_DigitalDownload WHERE ISBN LIKE '%" + s + "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Digital Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> searchDigBookAuthor(String s) {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_DigitalDownload WHERE Author LIKE '%" + s + "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Digital Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> searchDigBookTitleAndAuthor(String title, String author) {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_DigitalDownload WHERE title LIKE '%" + title + "%' AND author LIKE '%"
				+ author + "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Hard Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> searchHardBookTitle(String s) {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_HardCopy WHERE title LIKE '%" + s + "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Hard Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> searchHardBookISBN(String s) {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_HardCopy WHERE ISBN LIKE '%" + s + "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Hard Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> searchHardBookAuthor(String s) {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_HardCopy WHERE Author LIKE '%" + s + "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Hard Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Book> searchHardBookTitleAndAuthor(String title, String author) {
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_HardCopy WHERE title LIKE '%" + title + "%' AND author LIKE '%" + author
				+ "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Hard Book"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Movie> searchDigMovieTitle(String s) {
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_DigitalDownload WHERE title LIKE '%" + s + "%' ";
		Connection conn = connect();
		ResultSet result;
		try {
			// TODO search bar
			/*
			 * String sql =
			 * "SELECT * from Movies_DigitalDownload WHERE MATCH(title) against (?) "
			 * + "UNION ALL " +
			 * "SELECT * from Movies_Hardcopy WHERE MATCH(title) against (?)";
			 */

			Statement stmt = conn.prepareStatement(query);

			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Digital Movie"));

			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Movie> searchDigMovieYear(String s) {
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_DigitalDownload WHERE Year LIKE '%" + s + "%' ";
		Connection conn = connect();
		ResultSet result;
		try {
			// TODO search bar
			/*
			 * String sql =
			 * "SELECT * from Movies_DigitalDownload WHERE MATCH(title) against (?) "
			 * + "UNION ALL " +
			 * "SELECT * from Movies_Hardcopy WHERE MATCH(title) against (?)";
			 */

			Statement stmt = conn.prepareStatement(query);

			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Digital Movie"));

			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Movie> searchDigMovieTitleAndYear(String title, String year) {
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_DigitalDownload WHERE title LIKE '%" + title + "%' AND year LIKE '%" + year
				+ "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Digital Movie"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Movie> searchHardMovieTitle(String s) {
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_Hardcopy WHERE title LIKE '%" + s + "%' ";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);

			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Hard Movie"));

			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Movie> searchHardMovieYear(String s) {
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_Hardcopy WHERE Year LIKE '%" + s + "%' ";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);

			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Hard Movie"));

			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<Movie> searchHardMovieTitleAndYear(String title, String year) {
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_Hardcopy WHERE title LIKE '%" + title + "%' AND year LIKE '%" + year
				+ "%'";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Hard Movie"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static ObservableList<AllTypes> searchBookAndMovie(String s) {
		ObservableList<AllTypes> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_DigitalDownload, Movies_DigitalDownload WHERE Books_DigitalDownload.title LIKE '%"
				+ s + "%' AND Movies_DigitalDownload.title LIKE '%" + s + "%' ";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);

			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new AllTypes("Digital", result.getString("title"), result.getString("quantity"), result.getDouble("cost")));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	protected static void insert_DigBooks_customer() {
		ArrayList<String> phoneNum = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/Customer/phone");
		ArrayList<String> name = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/Customer/names");
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_DigitalDownload";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Digital"));

			}
			String sql = "INSERT INTO Customer(Item,Type,PhoneNumber,Name) VALUES (?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			for (int i = 0; i < 25; i++) {
				s.setString(1, a.get(i).getTitle());
				s.setString(2, "Digital Book");
				s.setString(3, phoneNum.get(i));
				s.setString(4, name.get(i));
				s.execute();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected static void insert_HardBooks_customer() {
		ArrayList<String> phoneNum = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/Customer/phone");
		ArrayList<String> name = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/Customer/names");
		ObservableList<Book> a = FXCollections.observableArrayList();
		String query = "SELECT * from Books_HardCopy";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Book(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDouble(5), "Hard"));

			}
			String sql = "INSERT INTO Customer(Item,Type,PhoneNumber,Name) VALUES (?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			for (int i = 0; i < 25; i++) {

				s.setString(1, a.get(i).getTitle());
				s.setString(2, "Hard Book");
				s.setString(3, phoneNum.get(i));
				s.setString(4, name.get(i));
				s.execute();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected static void insert_DigMovies_customer() {
		ArrayList<String> phoneNum = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/Customer/phone");
		ArrayList<String> name = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/Customer/names");
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_DigitalDownload";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Digital"));

			}
			String sql = "INSERT INTO Customer(Item,Type,PhoneNumber,Name) VALUES (?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			for (int i = 0; i < 25; i++) {

				s.setString(1, a.get(i).getTitle());
				s.setString(2, "Digital Movies");
				s.setString(3, phoneNum.get(i));
				s.setString(4, name.get(i));
				s.execute();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected static void insert_HardMovies_customer() {
		ArrayList<String> phoneNum = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/Customer/phone");
		ArrayList<String> name = FileIO.getFileContent("src/com/emad/CS431/Project1/Resources/Customer/names");
		ObservableList<Movie> a = FXCollections.observableArrayList();
		String query = "SELECT * from Movies_Hardcopy";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Movie(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4),
						result.getInt(5), "Hard"));

			}
			String sql = "INSERT INTO Customer(Item,Type,PhoneNumber,Name) VALUES (?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			for (int i = 0; i < 25; i++) {

				s.setString(1, a.get(i).getTitle());
				s.setString(2, "Hard Movies");
				s.setString(3, phoneNum.get(i));
				s.setString(4, name.get(i));
				// s.setString(3, );
				s.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected static ObservableList<Customer> getCustomers() {
		ObservableList<Customer> a = FXCollections.observableArrayList();
		String query = "SELECT * from Customer ORDER BY memberid";
		Connection conn = connect();
		ResultSet result;
		try {
			Statement stmt = conn.prepareStatement(query);
			result = stmt.executeQuery(query);
			while (result.next()) {
				a.add(new Customer(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
						result.getString(5)));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}
}
