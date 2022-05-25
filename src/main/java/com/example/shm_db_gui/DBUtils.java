package com.example.shm_db_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class DBUtils {
    public static void addArtefact(String number, String name, String size, String box) throws SQLException {
        Connection connection = null;
        PreparedStatement addSection = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
            addSection = connection.prepareStatement("insert into test.users (Number, Name, Size, BoxNo) " + "values(?,?,?,?)");

            addSection.setString (1, number);
            addSection.setString(2, name);
            addSection.setString(3, size);
            addSection.setString(4, box);
            addSection.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (addSection != null) {
                try {
                    addSection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void editArtefact(String number, String name, String size, String box) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");

            String sqlStatement = "UPDATE test.users SET Number='" + number + "', Name='"+ name +"', " +
                    "Size = '"+ size +"', BoxNo='"+ box +"' where Number='"+ number +"' ;";

            statement = connection.prepareStatement(sqlStatement);
            statement.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void deleteArtefact(String number) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");

            String sqlStatement = "DELETE FROM test.users WHERE Number='" + number + "';";

            statement = connection.prepareStatement(sqlStatement);
            statement.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ObservableList<Artefact> getItemData() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ObservableList<Artefact> list = FXCollections.observableArrayList();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
            statement = connection.prepareStatement("SELECT * FROM users");
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                list.add(new Artefact(Integer.parseInt(resultSet.getString("id")) ,resultSet.getString("Number"),
                        resultSet.getString("Name"), resultSet.getString("Size"), resultSet.getString("BoxNo") ));
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static void alertScreen(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.setTitle("Attention!");
        alert.showAndWait();
    }

    public static void changeScreen (ActionEvent event, String fxmlName) throws IOException {
        Parent home_page_parent = null;
        try {
            home_page_parent = FXMLLoader.load(Objects.requireNonNull(DBUtils.class.getResource(fxmlName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
