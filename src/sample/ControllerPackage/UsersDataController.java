package sample.ControllerPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DataBasePackage.Const;
import sample.Main;
import sample.DataPackage.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDataController {

    ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    private TextField CountField;

    @FXML
    private Button ExitButton, DeleteUser;

    @FXML
    private TableView<User> UsersTable;

    @FXML
    private TableColumn<User, String> IDColumn, FirstNameColumn, LastNameColumn, UserNameColumn, PasswordColumn, GenderColumn, AgeColumn, AdminColumn, BookColumn;

    @FXML
    void initialize() {
        ExitButton.setOnAction(event -> {
            Main.UpdateWindow("AdminWindow.fxml", ExitButton);
        });

        DeleteUser.setOnAction(actionEvent -> {
            Main.HandlerUsers.deleteUser(UsersTable.getSelectionModel().getSelectedItem());
            Main.HandlerBookRoom.deleteBook(UsersTable.getSelectionModel().getSelectedItem().getUserName());
            Main.HandlerQuery.deleteQueryByUserName(UsersTable.getSelectionModel().getSelectedItem().getUserName());
            Main.HandlerCash.deleteCash(UsersTable.getSelectionModel().getSelectedItem().getUserName());

            users.clear();
            UsersTable.getItems().clear();

            initializationTable();
        });

        initializationTable();
    }

    private void initData() {
        ResultSet result = Main.HandlerUsers.getUser();

        while(true) {
            try {
                if (!result.next()) break;

                users.add(new User(result.getString(Const.USER_ID), result.getString(Const.USER_FIRSTNAME), result.getString(Const.USER_LASTNAME),
                        result.getString(Const.USER_USERNAME), result.getString(Const.USER_PASSWORD), result.getString(Const.USER_GENDER),
                        result.getString(Const.USER_AGE), result.getString(Const.USER_ADMIN), result.getString(Const.USER_BOOK)));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        CountField.setText(String.valueOf(users.size()));
    }

    private void initializationTable() {
        initData();

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        UserNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        AgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        AdminColumn.setCellValueFactory(new PropertyValueFactory<>("admin"));
        BookColumn.setCellValueFactory(new PropertyValueFactory<>("book"));

        UsersTable.setItems(users);
        UsersTable.setVisible(true);
    }
}
