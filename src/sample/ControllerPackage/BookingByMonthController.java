package sample.ControllerPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DataBasePackage.Const;
import sample.Main;
import sample.DataPackage.Month;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingByMonthController {

    ObservableList<Month> month = FXCollections.observableArrayList();

    @FXML
    private TableView<Month> MonthTable;

    @FXML
    private TableColumn<String, Month> MonthColumn, CountBookingColumn;

    @FXML
    private Button ExitButton;

    @FXML
    void initialize() {
        ExitButton.setOnAction(event -> {
            updateMonthToNull();

            Main.UpdateWindow("AdminWindow.fxml", ExitButton);
        });

        countBookingByMonth();

        initializationTable();
    }

    private void initData() {
        ResultSet result = Main.HandlerMonth.getMonth();

        while(true) {
            try {
                if (!result.next()) break;

                month.add(new Month(result.getString(Const.MONTH_MONTH), Integer.parseInt(result.getString(Const.MONTH_COUNT))));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void initializationTable() {
        initData();

        MonthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        CountBookingColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        MonthTable.setItems(month);
        MonthTable.setVisible(true);
    }

    private void countBookingByMonth() {
        ResultSet resultMonth = Main.HandlerMonth.getMonth();
        ResultSet resultBooking = Main.HandlerBookRoom.getBookRoom();

        while (true) {
            try {
                resultMonth.next();
                if (!resultBooking.next()) break;
                String month = resultBooking.getString(Const.BOOKROOM_DATEBOOK).substring(5, 7);
                String monthString = numberToMonth(month);

                ResultSet result = Main.HandlerMonth.getCountByMonth(monthString);
                result.next();

                Main.HandlerMonth.updateMonth(monthString, Integer.parseInt(result.getString(Const.MONTH_COUNT)) + 1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private String numberToMonth(String number) {
        return switch (number) {
            case "01" -> "????????????";
            case "02" -> "??????????????";
            case "03" -> "????????";
            case "04" -> "????????????";
            case "05" -> "??????";
            case "06" -> "????????";
            case "07" -> "????????";
            case "08" -> "????????????";
            case "09" -> "????????????????";
            case "10" -> "??????????????";
            case "11" -> "????????????";
            case "12" -> "??????????????";
            default -> "";
        };
    }

    private void updateMonthToNull() {
        Main.HandlerMonth.updateMonth("????????????", 0);
        Main.HandlerMonth.updateMonth("??????????????", 0);
        Main.HandlerMonth.updateMonth("????????", 0);
        Main.HandlerMonth.updateMonth("????????????", 0);
        Main.HandlerMonth.updateMonth("??????", 0);
        Main.HandlerMonth.updateMonth("????????", 0);
        Main.HandlerMonth.updateMonth("????????", 0);
        Main.HandlerMonth.updateMonth("????????????", 0);
        Main.HandlerMonth.updateMonth("????????????????", 0);
        Main.HandlerMonth.updateMonth("??????????????", 0);
        Main.HandlerMonth.updateMonth("????????????", 0);
        Main.HandlerMonth.updateMonth("??????????????", 0);

    }
}
