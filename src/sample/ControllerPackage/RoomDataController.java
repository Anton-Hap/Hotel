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
import sample.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDataController {
    ObservableList<Room> rooms = FXCollections.observableArrayList();

    @FXML
    private Button ExitButton, DeleteRoom;

    @FXML
    private TableView<Room> RoomTable;

    @FXML
    private TextField CountField;

    @FXML
    private TableColumn<Room, String> IDColumn, TypeColumn, PriceColumn, SleepingPlaceCountColumn, SilenceLevelColumn;

    @FXML
    void initialize() {
        ExitButton.setOnAction(event -> {
            Main.UpdateWindow("AdminWindow.fxml", ExitButton);
        });

        DeleteRoom.setOnAction(actionEvent -> {
            Main.Handler.deleteRoom(RoomTable.getSelectionModel().getSelectedItem());

            rooms.clear();
            RoomTable.getItems().clear();

            initializationTable();
        });

        initializationTable();
    }

    private void initData() {
        ResultSet result = Main.Handler.getRooms();

        while(true) {
            try {
                if (!result.next()) break;

                rooms.add(new Room(result.getString(Const.ROOM_ID), result.getString(Const.ROOM_TYPE), result.getString(Const.ROOM_PRICE),
                        result.getString(Const.ROOM_SLEEPINGPLACECOUNT), result.getString(Const.ROOM_SILENCELEVEL)));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        CountField.setText(String.valueOf(rooms.size()));
    }

    private void initializationTable() {
        initData();

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        SleepingPlaceCountColumn.setCellValueFactory(new PropertyValueFactory<>("sleepingPlaceCount"));
        SilenceLevelColumn.setCellValueFactory(new PropertyValueFactory<>("silenceLevel"));

        RoomTable.setItems(rooms);
        RoomTable.setVisible(true);
    }
}
