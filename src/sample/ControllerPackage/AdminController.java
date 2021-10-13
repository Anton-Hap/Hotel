package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Main;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {

    @FXML
    private Button FoundUserButton, OutPutButton, ShowAllUsersButton, CreateRoomButton, DecisionBookTwoButton, ShowAllRoomButton, ShowAllBookingsButton, DecisionBookButton, ShowUsersByCashButton, BookingByMonthButton;

    @FXML
    void initialize() {
        DecisionBookTwoButton.setVisible(false);

        checkQuery();

        BookingByMonthButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("BookingByMonthWindow.fxml", BookingByMonthButton);
        });

        ShowUsersByCashButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("CashDataUsersWindow.fxml", ShowUsersByCashButton);
        });

        DecisionBookButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("DecisionBookWindow.fxml", DecisionBookButton);
        });

        DecisionBookTwoButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("DecisionBookWindow.fxml", DecisionBookButton);
        });

        ShowAllBookingsButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("BookingDataWindow.fxml", ShowAllBookingsButton);
        });

        ShowAllUsersButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("UsersDataWindow.fxml", ShowAllUsersButton);
        });

        FoundUserButton.setOnAction(actionEvent -> {
            Main.OpenNewWindow("FoundIDWindow.fxml", FoundUserButton);
        });

        OutPutButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("LogInWindow.fxml", OutPutButton);
        });

        CreateRoomButton.setOnAction(actionEvent -> {
            Main.OpenNewWindow("CreateRoomWindow.fxml",CreateRoomButton);
        });

        ShowAllRoomButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("RoomDataWindow.fxml", ShowAllRoomButton);
        });
    }

    private void checkQuery() {
        ResultSet result = Main.HandlerQuery.getQuery();

        try {
            if (result.next()) {
                DecisionBookTwoButton.setVisible(true);
                DecisionBookButton.setVisible(false);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
