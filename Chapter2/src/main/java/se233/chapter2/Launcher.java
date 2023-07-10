package se233.chapter2;
//autor: Seksan Jomchanasuk
import javafx.application.Application;
import javafx.stage.Stage;
import se233.chapter2.controller.FetchData;

public class Launcher extends Application {
    private static Stage primaryStage;
    @Override
    public  void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Currency Watcher");
        this.primaryStage.setResizable(false);
        System.out.println(FetchData.fetch_range("USD",6));
        this.primaryStage.show();

    }
}
