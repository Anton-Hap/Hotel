package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import sample.Data;
import sample.Main;

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

            if (!Data.ID.equals(""))
                Main.UpdateWindow("UserFoundWindow.fxml", FoundButton);
            else {
                MessageLabel.setTextFill(Color.web("Salmon"));
                MessageLabel.setText("Вы не ввели ID");
            }
        });
    }
}
