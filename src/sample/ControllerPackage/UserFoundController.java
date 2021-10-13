package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import sample.DataPackage.Data;
import sample.DataBasePackage.Const;
import sample.Main;
import sample.DataPackage.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class UserFoundController {

    User user;

    @FXML
    private Label MessageLabel;

    @FXML
    private Button SaveButton, ExitButton;

    @FXML
    private TextField IDField, FirstNameField, LastNameField, PasswordField, UserNameField, AgeField, GenderField, AdminField, BookField, CashField;

    @FXML
    void initialize() {
        WriteDataUser();

        Pattern pattern = Pattern.compile("[0-9]{0,3}");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (pattern.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        AgeField.setTextFormatter(formatter);

        Pattern patternOne = Pattern.compile("[0-1]{0,1}");
        UnaryOperator<TextFormatter.Change> filterOne = c -> {
            if (patternOne.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatterOne = new TextFormatter<>(filterOne);
        TextFormatter<String> formatterTwo = new TextFormatter<>(filterOne);
        AdminField.setTextFormatter(formatterOne);
        BookField.setTextFormatter(formatterTwo);

        Pattern patternThree = Pattern.compile("[0-9]{0,4}");
        UnaryOperator<TextFormatter.Change> filterThree = c -> {
            if (patternThree.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        TextFormatter<String> formatterThree = new TextFormatter<>(filterThree);
        CashField.setTextFormatter(formatterThree);

        Pattern patternTwo = Pattern.compile("[a-zA-z]*");
        UnaryOperator<TextFormatter.Change> filterTwo = c -> {
            if (patternTwo.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatterSix = new TextFormatter<>(filterTwo);
        TextFormatter<String> formatterSeven = new TextFormatter<>(filterTwo);
        FirstNameField.setTextFormatter(formatterSix);
        LastNameField.setTextFormatter(formatterSeven);

        Pattern patternFour = Pattern.compile("[a-zA-z0-9]*");
        UnaryOperator<TextFormatter.Change> filterFour = c -> {
            if (patternFour.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatterFour = new TextFormatter<>(filterFour);
        TextFormatter<String> formatterFive = new TextFormatter<>(filterFour);
        UserNameField.setTextFormatter(formatterFour);
        PasswordField.setTextFormatter(formatterFive);

      ExitButton.setOnAction(actionEvent -> {
          ExitButton.getScene().getWindow().hide();
      });

      SaveButton.setOnAction(actionEvent -> {
          ReadDataField();

          if (!user.getId().equals("") && !user.getFirstName().equals("") && !user.getLastName().equals("") && !user.getUserName().equals("") &&
                  !user.getPassword().equals("") && !user.getGender().equals("") && !user.getAge().equals("") && !user.getAdmin().equals("") &&
                  !user.getBook().equals("") && !Main.data.cash.equals("")) {
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
                      Main.HandlerUsers.updateUser(user);

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

    private void WriteDataUser() {
        user = Data.resultToUser(Main.HandlerUsers.getUserByID(Data.ID));

        IDField.appendText(user.getId());
        FirstNameField.appendText(user.getFirstName());
        LastNameField.appendText(user.getLastName());
        UserNameField.appendText(user.getUserName());
        PasswordField.appendText(user.getPassword());
        GenderField.appendText(user.getGender());
        AgeField.appendText(user.getAge());
        AdminField.appendText(user.getAdmin());
        BookField.appendText(user.getBook());
        CashField.appendText(getCash());
    }

    private void ReadDataField() {
        user.setId(IDField.getText());
        user.setFirstName(FirstNameField.getText());
        user.setLastName(LastNameField.getText());
        user.setUserName(UserNameField.getText());
        user.setPassword(PasswordField.getText());
        user.setGender(GenderField.getText());
        user.setAge(AgeField.getText());
        user.setAdmin(AdminField.getText());
        user.setBook(BookField.getText());

        Main.data.cash = CashField.getText();
    }

    private String getCash() {
        ResultSet resultCash = Main.HandlerCash.getCashByUserName(user.getUserName());
        String cash = "";


        try {
            resultCash.next();
            cash = resultCash.getString(Const.CASH_CASH);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cash;
    }
}
