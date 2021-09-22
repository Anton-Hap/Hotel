package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import sample.data.Data;
import sample.DataBasePackage.Const;
import sample.Main;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientController {

    @FXML
    private TextField CashField;

    @FXML
    private Label MessageLabel;

    @FXML
    private Button OutPutButton, BookRoomButton, CancelBookingButton, OrderFoodButton, ChangeDataButton, ClearButton;

    @FXML
    void initialize() {
        if (Data.ID.equals("1")) {
            MessageLabel.setTextFill(Color.web("SpringGreen"));
            MessageLabel.setText("Ваш запрос отправлен администратору");
        }

        ClearButton.setOnAction(actionEvent -> {
            MessageLabel.setText("");
        });

        CashField.setText(Integer.toString(getCash()));

        OrderFoodButton.setOnAction(actionEvent -> {
            if (Main.data.user.getBook().equals("1")) {
                Main.HandlerCash.updateCash(Main.data.user.getUserName(), (getCash() + 10));

                CashField.setText(Integer.toString(getCash()));

                MessageLabel.setTextFill(Color.web("SpringGreen"));
                MessageLabel.setText("Вы заказали еду в номер");
            } else {
                MessageLabel.setTextFill(Color.web("Salmon"));
                MessageLabel.setText("У вас нет номера");
            }
        });

        ChangeDataButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("RefactorDataUserWindow.fxml", ChangeDataButton);
        });

        CancelBookingButton.setOnAction(actionEvent -> {
            ResultSet result = Main.HandlerUsers.getUserByUserName(Main.data.user.getUserName());

            try {
                result.next();
                if (result.getString(Const.USER_BOOK).equals("1")) {
                    Main.HandlerUsers.updateUserBook(Main.data.user.getUserName(), "0");
                    Main.HandlerBookRoom.deleteBook(Main.data.user.getUserName());
                    Main.data.user.setBook("0");
                    Main.HandlerCash.updateCash(Main.data.user.getUserName(), 0);
                    CashField.setText("0");

                    MessageLabel.setTextFill(Color.web("SpringGreen"));
                    MessageLabel.setText("Бронь отменена");
                } else {
                    MessageLabel.setTextFill(Color.web("Salmon"));
                    MessageLabel.setText("У вас нет брони");
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });

        OutPutButton.setOnAction(event -> {
            MessageLabel.setText("");
            Data.ID = "";
            Main.UpdateWindow("LogInWindow.fxml", OutPutButton);
        });

        BookRoomButton.setOnAction(actionEvent -> {
            ResultSet resultQuery = Main.HandlerQuery.getQuery();
            ResultSet resultBook = Main.HandlerUsers.getUserByUserNameAndPassword(Main.data.user.getUserName(), Main.data.user.getPassword());
            int countQuery = 0, countBook = 0;

            while (true) {
                try {
                    if (!resultQuery.next()) break;

                    if (resultQuery.getString(Const.QUERY_USERNAMECLIENT).equals(Main.data.user.getUserName()))
                        countQuery++;
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }

            try {
                resultBook.next();
                if (resultBook.getString(Const.USER_BOOK).equals("1"))
                    countBook++;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

            if (countQuery > 0) {
                MessageLabel.setTextFill(Color.web("Salmon"));
                MessageLabel.setText("Вы уже отправили запрос на бронирования номера");
            } else if (countBook > 0) {
                MessageLabel.setTextFill(Color.web("Salmon"));
                MessageLabel.setText("У вас уже есть бронь");
            } else Main.UpdateWindow("ClientBookingWindow.fxml", BookRoomButton);


        });
    }

    private int getCash() {
        ResultSet resultCash = Main.HandlerCash.getCashByUserName(Main.data.user.getUserName());
        int cash = 0;

        try {
            resultCash.next();
            cash = resultCash.getInt(Const.CASH_CASH);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cash;
    }
}
