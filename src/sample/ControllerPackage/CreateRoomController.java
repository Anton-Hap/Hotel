package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import sample.Main;
import sample.data.Room;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class CreateRoomController {
    @FXML
    private Label MessageLabel;

    @FXML
    private TextField SleepingPlaceCountField, SilenceLevelField, PriceField, TypeField;

    @FXML
    private Button CreateButton, HelpButton;

    @FXML
        void initialize() {
        Pattern pattern = Pattern.compile("[1-3]{0,1}");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (pattern.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        TypeField.setTextFormatter(formatter);

        Pattern patternTwo = Pattern.compile("[1-4]{0,1}");
        UnaryOperator<TextFormatter.Change> filterTwo = c -> {
            if (patternTwo.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatterTwo = new TextFormatter<>(filterTwo);
        TextFormatter<String> formatterThree = new TextFormatter<>(filterTwo);
        SilenceLevelField.setTextFormatter(formatterTwo);
        SleepingPlaceCountField.setTextFormatter(formatterThree);

        Pattern patternThree = Pattern.compile("[0-9]{0,4}");
        UnaryOperator<TextFormatter.Change> filterThree = c -> {
            if (patternThree.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatterFour = new TextFormatter<>(filterThree);
        PriceField.setTextFormatter(formatterFour);

        SleepingPlaceCountField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                SilenceLevelField.requestFocus();
            }
        });

        SilenceLevelField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                PriceField.requestFocus();
            }
        });

        PriceField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                TypeField.requestFocus();
            }
        });

        TypeField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                CreateButton.fire();
            }
        });

        CreateButton.setOnAction(actionEvent -> {

            if (TypeField.getText().equals("") || PriceField.getText().equals("") || SilenceLevelField.getText().equals("") || SleepingPlaceCountField.getText().equals("")) {
                MessageLabel.setText("Вы оставили пустое поле");
            } else {
                Room room = new Room(TypeField.getText(), PriceField.getText(), SleepingPlaceCountField.getText(), SilenceLevelField.getText());

                Main.HandlerRoom.setRoom(room);

                CreateButton.getScene().getWindow().hide();
            }
        });

        HelpButton.setOnAction(actionEvent -> {
            Main.OpenNewWindow("HelpCreateRoomWindow.fxml", HelpButton);
        });
    }
}
