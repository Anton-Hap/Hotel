package sample.ControllerPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.BookRoom;
import sample.Cash;
import sample.DataBasePackage.Const;
import sample.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

public class CashDataUsersController {

    ObservableList<Cash> cash = FXCollections.observableArrayList();

    @FXML
    private TableView<Cash> CashTable;

    @FXML
    private TableColumn<String, Cash> IDColumn, CashColumn, UserNameColumn;

    @FXML
    private Button ExitButton;

    @FXML
    void initialize() {
        ExitButton.setOnAction(event -> {
            Main.UpdateWindow("AdminWindow.fxml", ExitButton);
        });

        initializationTable();
    }

    private void initData() {
        ResultSet result = Main.Handler.getCash();

        while(true) {
            try {
                if (!result.next()) break;

                cash.add(new Cash(result.getString(Const.CASH_ID), result.getString(Const.CASH_USERNAME), result.getString(Const.CASH_CASH)));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void initializationTable() {
        initData();

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        UserNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        CashColumn.setCellValueFactory(new PropertyValueFactory<>("cash"));

        CashTable.setItems(cash);
        CashTable.setVisible(true);
    }
}
