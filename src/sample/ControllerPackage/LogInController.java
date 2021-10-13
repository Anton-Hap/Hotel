package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import sample.DataBasePackage.Const;
import sample.Main;
import sample.DataPackage.User;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LogInController {

    @FXML
    private Label ErrorLabel;

    @FXML
    private TextField UserNameField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button LogInButton, RegisterButton;

    @FXML
    void initialize() {
        checkDateBook();

        UserNameField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                PasswordField.requestFocus();
            }
        });

        PasswordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                LogInButton.fire();
            }
        });

        RegisterButton.setOnAction(event -> {
            Main.UpdateWindow("SignUpWindow.fxml", RegisterButton);
        });

        LogInButton.setOnAction(actionEvent -> {
            String UserName = UserNameField.getText().trim();
            String Password = PasswordField.getText().trim();

            if(!UserName.equals("") && !Password.equals(""))
                logIn(UserName, Password);
            else ErrorLabel.setText("Вы оставили пустое поле");
        });
    }

    public void logIn(String UserName, String Password) {
        ResultSet result = Main.HandlerUsers.getUserByUserNameAndPassword(UserName, Password);

        try {
            if(result.next()) {
                System.out.println("Yoa are logged in");

                Main.data.user = new User(result.getString(Const.USER_ID), result.getString(Const.USER_FIRSTNAME), result.getString(Const.USER_LASTNAME), UserName, Password, result.getString(Const.USER_GENDER), result.getString(Const.USER_AGE), result.getString(Const.USER_ADMIN), result.getString(Const.USER_BOOK));

                Main.data.user.setUserName(UserName);
                Main.data.user.setPassword(Password);

                if (result.getInt(Const.USER_ADMIN) == 0)
                    Main.UpdateWindow("ClientWindow.fxml", LogInButton);
                else Main.UpdateWindow("AdminWindow.fxml", LogInButton);
            }
            else ErrorLabel.setText("Не найден такой пользователь");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void checkDateBook() {
        ResultSet result = Main.HandlerBookRoom.getBookRoom();

        while (true) {
            try {
                if (!result.next()) break;

                LocalDate date = LocalDate.of(Integer.parseInt(result.getString(Const.BOOKROOM_DATEBOOK).substring(0, 4)), Integer.parseInt(result.getString(Const.BOOKROOM_DATEBOOK).substring(5, 7)), Integer.parseInt(result.getString(Const.BOOKROOM_DATEBOOK).substring(8, 10)));

                if (date.compareTo(LocalDate.now().minusDays(Integer.parseInt(result.getString(Const.BOOKROOM_COUNTDAY)))) < 0) {
                    Main.HandlerBookRoom.deleteBook(result.getString(Const.BOOKROOM_USERNAMECLIENT));
                    Main.HandlerUsers.updateUserBook(result.getString(Const.BOOKROOM_USERNAMECLIENT), "0");
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
