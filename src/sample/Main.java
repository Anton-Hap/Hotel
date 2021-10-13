package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.DataBasePackage.*;
import sample.DataPackage.Data;

import java.io.IOException;

public class Main extends Application {

    public static RoomDatabase HandlerRoom = new RoomDatabase();
    public static UsersDatabase HandlerUsers = new UsersDatabase();
    public static QueryDatabase HandlerQuery = new QueryDatabase();
    public static MonthDatabase HandlerMonth = new MonthDatabase();
    public static CashDatabase HandlerCash = new CashDatabase();
    public static BookRoomDatabase HandlerBookRoom = new BookRoomDatabase();

    public static Data data = new Data();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPackage/LogInWindow.fxml"));
        primaryStage.setTitle("Hotel");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.getIcons().add(new Image("D:\\IdeaProjects\\Application\\src\\sample\\Images\\Icon.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void UpdateWindow(String addres, Button button) {
        Stage stage = (Stage) button.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/sample/FXMLPackage/" + addres));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }

    public static void OpenNewWindow(String addres, Button button) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/sample/FXMLPackage/" + addres));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
        stage.setTitle("Hotel");
        stage.getIcons().add(new Image("D:\\IdeaProjects\\Application\\src\\sample\\Images\\Icon.png"));
        stage.show();
        stage.centerOnScreen();
    }

}