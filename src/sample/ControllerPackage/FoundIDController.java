package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import sample.DataBasePackage.Const;
import sample.DataPackage.Data;
import sample.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class FoundIDController {

    @FXML
    private Label MessageLabel;

    @FXML
    private Button FoundButton;

    @FXML
    private TextField IDField;

    @FXML
    void initialize() {
        Pattern pattern = Pattern.compile("[0-9]{0,4}");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (pattern.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        IDField.setTextFormatter(formatter);

        IDField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                FoundButton.fire();
            }
        });

        FoundButton.setOnAction(actionEvent -> {
            Data.ID = IDField.getText();

            ResultSet result = Main.HandlerUsers.getUser();
            int count = 0;

            while (true) {
                try {
                    if(!result.next()) break;

                    if (Objects.equals(Data.ID, result.getString(Const.USER_ID))) count++;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            if (!Data.ID.equals(""))
                if (count == 1)
                    Main.UpdateWindow("UserFoundWindow.fxml", FoundButton);
                else {
                    MessageLabel.setTextFill(Color.web("Salmon"));
                    MessageLabel.setText("Такого ID не существует");
                }
            else {
                MessageLabel.setTextFill(Color.web("Salmon"));
                MessageLabel.setText("Вы не ввели ID");
            }
        });
    }
}
