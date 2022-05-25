package com.example.shm_db_gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.net.URL;

public class Welcome_Controller implements Initializable {
    @FXML
    public Button Enter;

    public void initialize(URL arg0, ResourceBundle arg1) {
        Enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtils.changeScreen(event, "add_screen.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}