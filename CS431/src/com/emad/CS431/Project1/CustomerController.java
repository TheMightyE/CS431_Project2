package com.emad.CS431.Project1;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerController {

	@FXML
	private TableView<Customer> table_customer;

	@FXML
	private TableColumn<Customer, Integer> col_id;

	@FXML
	private TableColumn<Customer, String> col_name;

	@FXML
	private TableColumn<Customer, String> col_item;

	@FXML
	private TableColumn<Customer, String> col_type;

	@FXML
	private TableColumn<Customer, String> col_phone;

	@FXML
	private void initialize() {
		initCustomerTable();
	}

	private void initCustomerTable() {
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_item.setCellValueFactory(new PropertyValueFactory<>("item"));
		col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		table_customer.setItems(DatabaseIO.getCustomers());
	}
}
