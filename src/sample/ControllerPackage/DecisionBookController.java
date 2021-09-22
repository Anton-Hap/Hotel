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
import sample.DataPackage.Query;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DecisionBookController {

    ObservableList<Query> query = FXCollections.observableArrayList();

    @FXML
    private TableView<Query> QueryTable;

    @FXML
    private TableColumn<String, Query> IdRoomColumn, DateStartColumn, CountDayColumn, UserNameColumn;

    @FXML
    private TextField CountField;

    @FXML
    private Button ExitButton, AcceptButton, RefuseButton;

    @FXML
    void initialize() {
        ExitButton.setOnAction(event -> {
            Main.UpdateWindow("AdminWindow.fxml", ExitButton);
        });

        AcceptButton.setOnAction(actionEvent -> {
            Main.HandlerBookRoom.setBookRoom(QueryTable.getSelectionModel().getSelectedItem());
            Main.HandlerQuery.deleteQuery(QueryTable.getSelectionModel().getSelectedItem());
            Main.HandlerUsers.updateUserBook(QueryTable.getSelectionModel().getSelectedItem().getUserName(), "1");

            int cash = 0;

            ResultSet result = Main.HandlerRoom.getRoomByID(QueryTable.getSelectionModel().getSelectedItem().getIdRoom());
            try {
                result.next();
                cash = result.getInt(Const.ROOM_PRICE);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            Main.HandlerCash.updateCash(QueryTable.getSelectionModel().getSelectedItem().getUserName(), cash);

            query.clear();
            QueryTable.getItems().clear();

            initializationTable();
        });

        RefuseButton.setOnAction(actionEvent -> {
            Main.HandlerQuery.deleteQuery(QueryTable.getSelectionModel().getSelectedItem());

            query.clear();
            QueryTable.getItems().clear();

            initializationTable();
        });

        initializationTable();
    }

    private void initData() {
        ResultSet result = Main.HandlerQuery.getQuery();

        while(true) {
            try {
                if (!result.next()) break;

                query.add(new Query(result.getString(Const.QUERY_USERNAMECLIENT), result.getString(Const.QUERY_IDROOM), result.getString(Const.QUERY_DATESTART),
                        result.getString(Const.QUERY_COUNTDAY)));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        CountField.setText(String.valueOf(query.size()));
    }

    private void initializationTable() {
        initData();

        UserNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        IdRoomColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
        DateStartColumn.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        CountDayColumn.setCellValueFactory(new PropertyValueFactory<>("countDay"));

        QueryTable.setItems(query);
        QueryTable.setVisible(true);
    }
}
