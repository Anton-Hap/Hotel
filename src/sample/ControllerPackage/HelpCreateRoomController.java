package sample.ControllerPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelpCreateRoomController {

    @FXML
    private Button ExitButton;

    @FXML
    void initialize(){
        ExitButton.setOnAction(actionEvent -> {
            ExitButton.getScene().getWindow().hide();
        });
    }
}
