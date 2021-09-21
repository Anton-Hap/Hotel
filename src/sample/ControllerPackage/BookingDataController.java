package sample.ControllerPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.BookRoom;
import sample.DataBasePackage.Const;
import sample.Main;
import sample.Room;
import sample.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDataController {

    ObservableList<BookRoom> books = FXCollections.observableArrayList();

    @FXML
    private TableView<BookRoom> BookingTable;

    @FXML
    private TextField CountField;

    @FXML
    private TableColumn<String, BookRoom> IDColumn, IDRoomColumn, DateStartColumn, CountDayColumn, UserNameColumn;

    @FXML
    private Button ExitButton, DeleteBookingButton;

    @FXML
    void initialize() {
        ExitButton.setOnAction(event -> {
            Main.UpdateWindow("AdminWindow.fxml", ExitButton);
        });

        DeleteBookingButton.setOnAction(actionEvent -> {
            Main.Handler.deleteBook(BookingTable.getSelectionModel().getSelectedItem().getUserName());
            Main.Handler.updateUserBook(BookingTable.getSelectionModel().getSelectedItem().getUserName(), "0");
            Main.Handler.updateCash(BookingTable.getSelectionModel().getSelectedItem().getUserName(), 0);

            books.clear();
            BookingTable.getItems().clear();

            initializationTable();
        });

        initializationTable();
    }

    private void initData() {
        ResultSet result = Main.Handler.getBookRoom();

        while(true) {
            try {
                if (!result.next()) break;

                books.add(new BookRoom(result.getString(Const.BOOKROOM_ID), result.getString(Const.BOOKROOM_IDROOM), result.getString(Const.BOOKROOM_DATEBOOK),
                        result.getString(Const.BOOKROOM_COUNTDAY), result.getString(Const.BOOKROOM_USERNAMECLIENT)));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        CountField.setText(String.valueOf(books.size()));
    }

    private void initializationTable() {
        initData();

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        IDRoomColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
        DateStartColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        CountDayColumn.setCellValueFactory(new PropertyValueFactory<>("countDay"));
        UserNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        BookingTable.setItems(books);
        BookingTable.setVisible(true);
    }
}
