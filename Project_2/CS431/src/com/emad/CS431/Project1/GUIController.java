package com.emad.CS431.Project1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUIController {

	@FXML
	private Button btn_search;

	@FXML
	private HBox hbox_search_all;

	@FXML
	private VBox book_search_fields;

	@FXML
	private HBox movie_search_fields;

	@FXML
	private TextField tf_search_all;

	@FXML
	private TextField tf_book_title;

	@FXML
	private TextField tf_book_isbn;

	@FXML
	private TextField tf_book_author;

	@FXML
	private TextField tf_movie_title;

	@FXML
	private TextField tf_movie_year;

	@FXML
	private TextField tf_book_cost_low;

	@FXML
	private TextField tf_book_cost_high;

	@FXML
	private TableColumn<Book, String> col_book_isbn;

	@FXML
	private TableColumn<Book, String> col_book_hardDig;

	@FXML
	private TableColumn<Book, String> col_book_title;

	@FXML
	private TableColumn<Book, String> col_book_auth;

	@FXML
	private TableColumn<Book, String> col_book_qty;

	@FXML
	private TableColumn<Book, Double> col_book_cost;

	@FXML
	private TableView<Book> table_books;

	@FXML
	private TableView<AllTypes> table_all;

	@FXML
	private ComboBox<String> combo;

	@FXML
	private TableView<Movie> table_movies;

	@FXML
	private TableColumn<Movie, Integer> col_movie_year;

	@FXML
	private TableColumn<Movie, String> col_movie_hardDig;

	@FXML
	private TableColumn<Movie, String> col_movie_qty;

	@FXML
	private TableColumn<Movie, Integer> col_movie_title;

	@FXML
	private TableColumn<Movie, Integer> col_movie_cost;

	@FXML
	private TableColumn<AllTypes, String> col_all_title;

	@FXML
	private TableColumn<AllTypes, String> col_all_qty;

	@FXML
	private TableColumn<AllTypes, Double> col_all_cost;

	@FXML
	private TableColumn<AllTypes, String> col_all_type;

	@FXML
	private void initialize() {
		btn_search.setOnAction(e -> {
			if (!tf_book_cost_low.getText().isEmpty() && !tf_book_cost_high.getText().isEmpty()) {
				getBookCostBetween();
			}
		});
		initComboBox();
		initAllTable();
		initBookTitleSearch();
		initBookISBNSearch();
		initBookAuthorSearch();
		initMovieTitleSearch();
		initMovieYearSearch();
		initSearchAll();
		book_search_fields.setVisible(true);
		movie_search_fields.setVisible(false);
		table_books.setVisible(true);
		table_movies.setVisible(false);
		initDigBookTable();
		// initDigMovieTable();
		// initDigBookTable();

	}

	private void initDigBookTable() {
		ObservableList<Book> digBooks = DatabaseIO.getDigitalBooks();
		// hardBooks = DatabaseIO.getHardBooks();
		col_book_isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		col_book_hardDig.setCellValueFactory(new PropertyValueFactory<>("type"));
		col_book_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_book_auth.setCellValueFactory(new PropertyValueFactory<>("author"));
		col_book_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		col_book_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

		table_books.setItems(digBooks);

	}

	private void initHardBookTable() {
		ObservableList<Book> hardBooks = DatabaseIO.getHardBooks();
		// hardBooks = DatabaseIO.getHardBooks();
		col_book_isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		col_book_hardDig.setCellValueFactory(new PropertyValueFactory<>("type"));
		col_book_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_book_auth.setCellValueFactory(new PropertyValueFactory<>("author"));
		col_book_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		col_book_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

		table_books.setItems(hardBooks);

	}

	private void initDigMovieTable() {
		ObservableList<Movie> digMovies = DatabaseIO.getDigitalMovies();

		col_movie_hardDig.setCellValueFactory(new PropertyValueFactory<>("type"));
		col_movie_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_movie_year.setCellValueFactory(new PropertyValueFactory<>("year"));
		col_movie_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		col_movie_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

		table_movies.setItems(digMovies);

	}

	private void initHardMovieTable() {
		ObservableList<Movie> hardMovies = DatabaseIO.getHardMovies();

		col_movie_hardDig.setCellValueFactory(new PropertyValueFactory<>("type"));
		col_movie_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_movie_year.setCellValueFactory(new PropertyValueFactory<>("year"));
		col_movie_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		col_movie_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

		table_movies.setItems(hardMovies);

	}

	private void initAllTable() {
		ObservableList<AllTypes> all = DatabaseIO.getAll();

		col_all_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		col_all_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_all_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		col_all_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

		table_all.setItems(all);

	}

	@SuppressWarnings("unused")
	private <T> ObservableList<T> combineTwoLists(ObservableList<T> list1, ObservableList<T> list2) {
		ObservableList<T> l = FXCollections.observableArrayList();
		for (int i = 0; i < list1.size(); i++) {
			l.add(list1.get(i));
		}

		for (int i = 0; i < list2.size(); i++) {
			l.add(list2.get(i));
		}

		return l;
	}

	private void initBookTitleSearch() {
		tf_book_title.textProperty().addListener((observable, oldValue, newValue) -> {
			if (tf_book_author.getText().isEmpty()) {
				if (combo.getValue().equals("Digital Books")) {
					table_books.setItems(DatabaseIO.searchDigBookTitle(newValue));

				} else if (combo.getValue().equals("Hardcopy Books")) {
					table_books.setItems(DatabaseIO.searchHardBookTitle(newValue));
				}
			} else {
				if (combo.getValue().equals("Digital Books")) {
					table_books.setItems(DatabaseIO.searchDigBookTitle(newValue));

				} else if (combo.getValue().equals("Hardcopy Books")) {
					table_books.setItems(DatabaseIO.searchHardBookTitleAndAuthor(newValue, tf_book_author.getText()));
				}
			}

		});
	}

	private void initBookISBNSearch() {
		tf_book_isbn.textProperty().addListener((observable, oldValue, newValue) -> {
			if (combo.getValue().equals("Digital Books")) {
				table_books.setItems(DatabaseIO.searchDigBookISBN(newValue));

			} else if (combo.getValue().equals("Hardcopy Books")) {
				table_books.setItems(DatabaseIO.searchHardBookISBN(newValue));
			}

		});
	}

	private void initBookAuthorSearch() {
		tf_book_author.textProperty().addListener((observable, oldValue, newValue) -> {
			if (tf_book_title.getText().isEmpty()) {
				if (combo.getValue().equals("Digital Books")) {
					table_books.setItems(DatabaseIO.searchDigBookAuthor(newValue));

				} else if (combo.getValue().equals("Hardcopy Books")) {
					table_books.setItems(DatabaseIO.searchHardBookAuthor(newValue));
				}
			} else {
				if (combo.getValue().equals("Digital Books")) {
					table_books.setItems(DatabaseIO.searchDigBookTitleAndAuthor(tf_book_title.getText(), newValue));

				} else if (combo.getValue().equals("Hardcopy Books")) {
					table_books.setItems(DatabaseIO.searchHardBookTitleAndAuthor(tf_book_title.getText(), newValue));
				}
			}

		});
	}

	private void getBookCostBetween() {
		table_books.setItems(DatabaseIO.getDigitalBooksCostBetween(Double.parseDouble(tf_book_cost_low.getText()),
				Double.parseDouble(tf_book_cost_high.getText())));
	}

	private void initMovieTitleSearch() {
		tf_movie_title.textProperty().addListener((observable, oldValue, newValue) -> {
			if (tf_movie_year.getText().isEmpty()) {
				if (combo.getValue().equals("Digital Movies")) {
					table_movies.setItems(DatabaseIO.searchDigMovieTitle(newValue));

				} else if (combo.getValue().equals("Hardcopy Movies")) {
					table_movies.setItems(DatabaseIO.searchHardMovieTitle(newValue));
				}
			} else {
				if (combo.getValue().equals("Digital Movies")) {
					table_movies.setItems(DatabaseIO.searchDigMovieTitleAndYear(newValue, tf_movie_year.getText()));

				} else if (combo.getValue().equals("Hardcopy Movies")) {
					table_movies.setItems(DatabaseIO.searchHardMovieTitleAndYear(newValue, tf_movie_year.getText()));
				}

			}

		});
	}

	private void initMovieYearSearch() {
		tf_movie_year.textProperty().addListener((observable, oldValue, newValue) -> {
			if (tf_movie_title.getText().isEmpty()) {
				if (combo.getValue().equals("Digital Movies")) {
					table_movies.setItems(DatabaseIO.searchDigMovieYear(newValue));

				} else if (combo.getValue().equals("Hardcopy Movies")) {
					table_movies.setItems(DatabaseIO.searchHardMovieYear(newValue));
				}
			} else {
				if (combo.getValue().equals("Digital Movies")) {
					table_movies.setItems(DatabaseIO.searchDigMovieTitleAndYear(newValue, tf_movie_title.getText()));

				} else if (combo.getValue().equals("Hardcopy Movies")) {
					table_movies.setItems(DatabaseIO.searchHardMovieTitleAndYear(newValue, tf_movie_title.getText()));
				}

			}

		});
	}

	private void initSearchAll() {
		tf_search_all.textProperty().addListener((observable, oldValue, newValue) -> {
			if (combo.getValue().equals("All")) {
				table_all.setItems(DatabaseIO.searchBookAndMovie(newValue));
			}
		});

	}

	private void initComboBox() {
		ObservableList<String> l = FXCollections.observableArrayList();
		l.add("Digital Books");
		l.add("Hardcopy Books");
		l.add("Digital Movies");
		l.add("Hardcopy Movies");
		l.add("All");

		combo.getItems().addAll(l);
		combo.setValue("Digital Books");

		combo.valueProperty().addListener(new ChangeListener<String>() {
			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue ov, String oldVal, String newVal) {
				if (newVal.equals("Digital Books")) {
					initDigBookTable();

					movie_search_fields.setVisible(false);
					book_search_fields.setVisible(true);
					hbox_search_all.setVisible(false);

					table_all.setVisible(false);
					table_books.setVisible(true);
					table_movies.setVisible(false);

				} else if (newVal.equals("Hardcopy Books")) {
					initHardBookTable();

					movie_search_fields.setVisible(false);
					book_search_fields.setVisible(true);
					hbox_search_all.setVisible(false);

					table_all.setVisible(false);
					table_books.setVisible(true);
					table_movies.setVisible(false);

				} else if (newVal.equals("Digital Movies")) {
					initDigMovieTable();

					movie_search_fields.setVisible(true);
					book_search_fields.setVisible(false);
					hbox_search_all.setVisible(false);

					table_all.setVisible(false);
					table_books.setVisible(false);
					table_movies.setVisible(true);

				} else if (newVal.equals("Hardcopy Movies")) {
					initHardMovieTable();

					movie_search_fields.setVisible(true);
					book_search_fields.setVisible(false);
					hbox_search_all.setVisible(false);

					table_all.setVisible(false);
					table_books.setVisible(false);
					table_movies.setVisible(true);

				} else {
					initAllTable();
					movie_search_fields.setVisible(false);
					book_search_fields.setVisible(false);
					hbox_search_all.setVisible(true);

					table_all.setVisible(true);
					table_books.setVisible(false);
					table_movies.setVisible(false);

				}
			}
		});

	}

}
