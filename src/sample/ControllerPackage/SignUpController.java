package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import sample.DataBasePackage.Const;
import sample.Main;
import sample.DataPackage.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SignUpController {
    @FXML
    private Label label;

    @FXML
    private TextField FirstNameField, LastNameField, PasswordShowField, UserNameField, AgeField;

    @FXML
    private PasswordField PasswordHiddenField;

    @FXML
    private RadioButton RadioButtonMale, RadioButtonFemale, ShowRadioButton;

    @FXML
    private Button LogInButton, PrevButton;

    @FXML
    void initialize() {
        PasswordShowField.setVisible(false);

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
        TextFormatter<String> formatterSix = new TextFormatter<>(filterThree);
        UserNameField.setTextFormatter(formatterFour);
        PasswordHiddenField.setTextFormatter(formatterFive);
        PasswordShowField.setTextFormatter(formatterSix);

        ShowRadioButton.setOnAction(actionEvent -> {
            if (ShowRadioButton.isSelected()) {
                PasswordHiddenField.setVisible(false);
                PasswordShowField.setText(PasswordHiddenField.getText());
                PasswordShowField.setVisible(true);
            } else {
                PasswordShowField.setVisible(false);
                PasswordHiddenField.setText(PasswordShowField.getText());
                PasswordHiddenField.setVisible(true);
            }
        });

        FirstNameField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                LastNameField.requestFocus();
            }
        });

        LastNameField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                UserNameField.requestFocus();
            }
        });

        UserNameField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (ShowRadioButton.isSelected())
                    PasswordShowField.requestFocus();
                else PasswordHiddenField.requestFocus();
            }
        });

        PasswordHiddenField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                AgeField.requestFocus();
            }
        });

        PasswordShowField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                AgeField.requestFocus();
            }
        });

        AgeField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                FirstNameField.requestFocus();
            }
        });

        ToggleGroup group = new ToggleGroup();
        RadioButtonFemale.setToggleGroup(group);
        RadioButtonMale.setToggleGroup(group);

        LogInButton.setOnAction(actionEvent -> {
            String gender = "";
            if(RadioButtonMale.isSelected())
                gender = "Мужчина";
            else gender = "Женщина";


            String password = "";

            if (ShowRadioButton.isSelected())
                password = PasswordShowField.getText();
            else password = PasswordHiddenField.getText();

            User user = new User(FirstNameField.getText(), LastNameField.getText(), UserNameField.getText(), password, gender, AgeField.getText(), "0", "0");

            if(!user.getFirstName().equals("") && !user.getLastName().equals("") && !user.getUserName().equals("") && !user.getPassword().equals("") &&
                    !user.getGender().equals("") && !user.getAge().equals("")) {
                if (user.getGender().equals("Мужчина") || user.getGender().equals("Женщина")) {
                    ResultSet result = Main.HandlerUsers.getUser();
                    int count = 0;

                    while (true) {
                        try {
                            if (!result.next()) break;

                            if (result.getString(Const.USER_USERNAME).equals(user.getUserName()) && !result.getString(Const.USER_ID).equals(user.getId()))
                                count++;
                        } catch (SQLException throwables)
                        {
                            throwables.printStackTrace();
                        }
                    }

                    if (count == 0) {
                        Main.HandlerUsers.setUser(user);
                        Main.HandlerCash.setCashClient(user.getUserName());

                        Main.UpdateWindow("LogInWindow.fxml", LogInButton);
                    } else {
                        label.setTextFill(Color.web("Salmon"));
                        label.setText("Такой логин уже существует");
                    }
                } else {
                    label.setTextFill(Color.web("Salmon"));
                    label.setText("Вы неправильно указали пол (Мужчина/Женщина)");
                }
            } else label.setText("Недостаточно данных");
        });

        PrevButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("LogInWindow.fxml", PrevButton);
        });
    }

}
