package sample.ControllerPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Cash;
import sample.DataBasePackage.Const;
import sample.Main;
import sample.Month;

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
        ResultSet result = Main.Handler.getMonth();

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
        ResultSet resultMonth = Main.Handler.getMonth();
        ResultSet resultBooking = Main.Handler.getBookRoom();

        while (true) {
            try {
                resultMonth.next();
                if (!resultBooking.next()) break;
                String month = resultBooking.getString(Const.BOOKROOM_DATEBOOK).substring(5, 7);
                String monthString = numberToMonth(month);

                ResultSet result = Main.Handler.getCountByMonth(monthString);
                result.next();

                Main.Handler.updateMonth(monthString, Integer.parseInt(result.getString(Const.MONTH_COUNT)) + 1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private String numberToMonth(String number) {
        return switch (number) {
            case "01" -> "Январь";
            case "02" -> "Февраль";
            case "03" -> "Март";
            case "04" -> "Апрель";
            case "05" -> "Май";
            case "06" -> "Июнь";
            case "07" -> "Июль";
            case "08" -> "Август";
            case "09" -> "Сентябрь";
            case "10" -> "Октябрь";
            case "11" -> "Ноябрь";
            case "12" -> "Декабрь";
            default -> "";
        };
    }

    private void updateMonthToNull() {
        Main.Handler.updateMonth("Январь", 0);
        Main.Handler.updateMonth("Февраль", 0);
        Main.Handler.updateMonth("Март", 0);
        Main.Handler.updateMonth("Апрель", 0);
        Main.Handler.updateMonth("Май", 0);
        Main.Handler.updateMonth("Июнь", 0);
        Main.Handler.updateMonth("Июль", 0);
        Main.Handler.updateMonth("Август", 0);
        Main.Handler.updateMonth("Сентябрь", 0);
        Main.Handler.updateMonth("Октябрь", 0);
        Main.Handler.updateMonth("Ноябрь", 0);
        Main.Handler.updateMonth("Декабрь", 0);

    }
}
