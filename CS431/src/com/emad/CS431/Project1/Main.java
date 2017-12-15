package com.emad.CS431.Project1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// DatabaseIO.initIntoDigBooks();
		// DatabaseIO.initIntoDigMovies();
		// DatabaseIO.insert_DigBooks_customer();
		// DatabaseIO.insert_HardBooks_customer();
		// DatabaseIO.insert_DigMovies_customer();
		// DatabaseIO.insert_HardMovies_customer();

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent customersRoot = FXMLLoader.load(getClass().getResource("Resources/FXML/customer.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(customersRoot));
		stage.setTitle("Customers");
		stage.show();

		Parent recordsRoot = FXMLLoader.load(getClass().getResource("Resources/FXML/gui.fxml"));
		primaryStage.setScene(new Scene(recordsRoot));
		primaryStage.setTitle("Records");
		primaryStage.show();

	}
}
