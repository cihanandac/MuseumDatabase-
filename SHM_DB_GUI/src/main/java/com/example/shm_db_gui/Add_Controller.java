package com.example.shm_db_gui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Add_Controller implements Initializable {
    @FXML
    private Button add;

    @FXML
    private TextField box;

    @FXML
    private Button delete;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Artefact, Integer> id_table;

    @FXML
    private TableColumn<Artefact, String> name_table;

    @FXML
    private TableColumn<Artefact, String> number_table;

    @FXML
    private TableColumn<Artefact, String> size_table;

    @FXML
    private TableColumn<Artefact, String> box_table;

    @FXML
    private TableView<Artefact> item_table;

    @FXML
    private TextField number;

    @FXML
    private TextField size;

    @FXML
    private Button update;

    @FXML
    private TextField search;

    ObservableList<Artefact> listM;
    int index = -1;
    ObservableList<Artefact> listData;






    public void initialize(URL location, ResourceBundle resources){

        updateTable();

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int num = Integer.parseInt(number.getText());
                try {
                    DBUtils.addArtefact(number.getText() , name.getText(), size.getText(), box.getText());
                    updateTable();
                    searchTable();
                } catch (SQLException e) {
                    DBUtils.alertScreen(e.toString());
                }
            }
        });

        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    DBUtils.editArtefact(number.getText(), name.getText(), size.getText(), box.getText());
                    updateTable();
                    searchTable();
                } catch (SQLException e) {
                    DBUtils.alertScreen(e.toString());
                }
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.deleteArtefact(number.getText());
                updateTable();
                searchTable();
            }
        });


    }

    @FXML
    public void getSelected(MouseEvent mouseEvent) {
        int index = item_table.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }
        number.setText(number_table.getCellData(index).toString());
        name.setText(name_table.getCellData(index).toString());
        size.setText(size_table.getCellData(index).toString());
        box.setText(box_table.getCellData(index).toString());

    }

    public void updateTable(){
        id_table.setCellValueFactory(new PropertyValueFactory<Artefact, Integer>("id"));
        number_table.setCellValueFactory(new PropertyValueFactory<Artefact, String>("number"));
        name_table.setCellValueFactory(new PropertyValueFactory<Artefact, String>("name"));
        size_table.setCellValueFactory (new PropertyValueFactory<Artefact, String>("size"));
        box_table.setCellValueFactory(new PropertyValueFactory<Artefact, String>("boxNo"));

        listM = DBUtils.getItemData();
        item_table.setItems(listM);
    }

    public void searchTable(){
        id_table.setCellValueFactory(new PropertyValueFactory<Artefact, Integer>("id"));
        number_table.setCellValueFactory(new PropertyValueFactory<Artefact, String>("number"));
        name_table.setCellValueFactory(new PropertyValueFactory<Artefact, String>("name"));
        size_table.setCellValueFactory (new PropertyValueFactory<Artefact, String>("size"));
        box_table.setCellValueFactory(new PropertyValueFactory<Artefact, String>("boxNo"));

        listData = DBUtils.getItemData();
        item_table.setItems(listData);

        FilteredList<Artefact> filteredData = new FilteredList<>(listData, b->true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(item.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (item.getNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else{
                    return false;
                }

            });

        });
        SortedList<Artefact> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(item_table.comparatorProperty());
        item_table.setItems(sortedData);
    }
}
