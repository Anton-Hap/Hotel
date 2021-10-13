package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import sample.DataBasePackage.Const;
import sample.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class RefactorDataUserController {

    @FXML
    private Label MessageLabel;

    @FXML
    private Button SaveButton, ExitButton;

    @FXML
    private TextField FirstNameField, LastNameField, PasswordField, UserNameField, AgeField, GenderField;

    @FXML
    void initialize() {
        WriteDataUser();

        Pattern pattern = Pattern.compile("[0-9]{0,3}");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (pattern.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        AgeField.setTextFormatter(formatter);

        Pattern patternTwo = Pattern.compile("[a-zA-z]*");
        UnaryOperator<TextFormatter.Change> filterTwo = c -> {
            if (patternTwo.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatterTwo = new TextFormatter<>(filterTwo);
        TextFormatter<String> formatterThree = new TextFormatter<>(filterTwo);
        FirstNameField.setTextFormatter(formatterTwo);
        LastNameField.setTextFormatter(formatterThree);

        Pattern patternThree = Pattern.compile("[a-zA-z0-9]*");
        UnaryOperator<TextFormatter.Change> filterThree = c -> {
            if (patternThree.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatterFour = new TextFormatter<>(filterThree);
        TextFormatter<String> formatterFive = new TextFormatter<>(filterThree);
        UserNameField.setTextFormatter(formatterFour);
        PasswordField.setTextFormatter(formatterFive);


        ExitButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("ClientWindow.fxml", ExitButton);
        });

        SaveButton.setOnAction(actionEvent -> {
            ReadDataField();

            if (!Main.data.user.getUserName().equals("") && !Main.data.user.getFirstName().equals("") && !Main.data.user.getLastName().equals("") &&
                    !Main.data.user.getPassword().equals("") && !Main.data.user.getAge().equals("") && !Main.data.user.getGender().equals("")) {
                if (Main.data.user.getGender().equals("Мужчина") || Main.data.user.getGender().equals("Женщина")) {
                    ResultSet result = Main.HandlerUsers.getUser();
                    int count = 0;

                    while (true) {
                        try {
                            if (!result.next()) break;

                            if (result.getString(Const.USER_USERNAME).equals(Main.data.user.getUserName()) && !result.getString(Const.USER_ID).equals(Main.data.user.getId()))
                                count++;
                        } catch (SQLException throwables)
                        {
                            throwables.printStackTrace();
                        }
                    }

                    if (count == 0) {
                        Main.HandlerUsers.updateUser(Main.data.user);

                        MessageLabel.setTextFill(Color.web("SpringGreen"));
                        MessageLabel.setText("Сохранено");
                    } else {
                        MessageLabel.setTextFill(Color.web("Salmon"));
                        MessageLabel.setText("Такой логин уже существует");
                    }
                } else {
                    MessageLabel.setTextFill(Color.web("Salmon"));
                    MessageLabel.setText("Вы неправильно указали пол (Мужчина/Женщина)");
                }
            } else {
                MessageLabel.setTextFill(Color.web("Salmon"));
                MessageLabel.setText("Вы оставили пустое поле");
            }
        });
    }

    public void WriteDataUser() {
        FirstNameField.appendText(Main.data.user.getFirstName());
        LastNameField.appendText(Main.data.user.getLastName());
        UserNameField.appendText(Main.data.user.getUserName());
        PasswordField.appendText(Main.data.user.getPassword());
        GenderField.appendText(Main.data.user.getGender());
        AgeField.appendText(Main.data.user.getAge());
    }

    public void ReadDataField() {
        Main.data.user.setFirstName(FirstNameField.getText());
        Main.data.user.setLastName(LastNameField.getText());
        Main.data.user.setUserName(UserNameField.getText());
        Main.data.user.setPassword(PasswordField.getText());
        Main.data.user.setGender(GenderField.getText());
        Main.data.user.setAge(AgeField.getText());
    }
}