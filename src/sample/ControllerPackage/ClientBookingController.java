package sample.ControllerPackage;

import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.MediaException;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import sample.Data;
import sample.Main;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientBookingController {

    ObservableList<String> list;

    @FXML
    private Button BookRoomButton, ExitButton;

    @FXML
    private TextField CountDayField;

    @FXML
    private DatePicker DatePicker;

    @FXML
    private Label Label;

    @FXML
    private ChoiceBox<String> RoomChoiceBox;

    @FXML
    void initialize() {
        ExitButton.setOnAction(actionEvent -> {
            Main.UpdateWindow("ClientWindow.fxml", ExitButton);
        });

        DatePicker.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
            }
        });

        loadingChoiceBox();

        BookRoomButton.setOnAction(actionEvent -> {
            if (!CountDayField.getText().equals(""))
                if(!RoomChoiceBox.getSelectionModel().isEmpty())
                   if (DatePicker.getValue() != null) {
                       LocalDate date = DatePicker.getValue();
                       String dateS = date.toString();

                       Main.Handler.setQuery(Main.data.user.getUserName(), idRoom(), dateS, CountDayField.getText());
                       Data.ID = "1";
                       Main.UpdateWindow("ClientWindow.fxml", BookRoomButton);
                   }
                   else Label.setText("Вы не выбрали дату");
                else Label.setText("Вы не выбрали комнату");
            else Label.setText("Вы не указали количество дней");
        });
    }

    private String idRoom() {
        String s = RoomChoiceBox.getValue();
        int i = s.indexOf(" "), j = s.indexOf(":");

        return s.substring(j + 1, i);
    }

    private void loadingChoiceBox() {
        ResultSet result = Main.Handler.getRooms();
        Main.data.getRoomsData(result);

        ArrayList<String> rooms = new ArrayList<>();

        for (int i = 0; i < Main.data.RoomID.size(); i++)
            rooms.add("ID:" + Main.data.RoomID.get(i) + " | " + Main.data.RoomType.get(i) + " | Количество спальных мест: " + Main.data.RoomCountSleepingPlace.get(i) + " | Уровень тишины: " + Main.data.RoomSilenceLevel.get(i) + " | Цена: " + Main.data.RoomPrice.get(i));

        list = FXCollections.observableArrayList(rooms);

        RoomChoiceBox.setItems(list);
    }
}