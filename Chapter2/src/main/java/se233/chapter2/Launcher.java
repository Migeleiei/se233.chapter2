package se233.chapter2;
//autor: Seksan Jomchanasuk
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se233.chapter2.controller.Initialize;
import se233.chapter2.controller.RefreshTask;
import se233.chapter2.model.Currency;
import se233.chapter2.view.CurrencyParentPane;
import se233.chapter2.view.TopPane;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Launcher extends Application {
    private static Stage primaryStage;
    private static Scene mainScene;
    private static FlowPane mainPane;
    private static TopPane topPane;
    private static CurrencyParentPane currencyParentPane;
    private static ArrayList<Currency> currencyList;
    @Override
    public  void start(Stage primaryStage) throws ExecutionException, InterruptedException{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Currency Watcher");
        this.currencyList = Initialize.initialize_app();
        this.currencyList = Initialize.initialize_app();
        initMainPane();
        mainScene = new Scene(mainPane);
        this.primaryStage.setScene(mainScene);
        this.primaryStage.show();
        RefreshTask r = new RefreshTask();
        Thread th = new Thread(r);
        th.setDaemon(true);
        th.start();
    }
    public void initMainPane() throws ExecutionException, InterruptedException{
        mainPane = new FlowPane();
        topPane = new TopPane();
        currencyParentPane = new CurrencyParentPane(this.currencyList);
        mainPane.getChildren().add(topPane);
        mainPane.getChildren().add(currencyParentPane);
    }
    public static void refreshPane() throws InterruptedException, ExecutionException{
        topPane.refreshPane();
        currencyParentPane.refreshPane(currencyList);
        primaryStage.sizeToScene();
    }

    public static CurrencyParentPane getCurrencyParentPane() {
        return currencyParentPane;
    }

    public static void setCurrencyParentPane(CurrencyParentPane currencyParentPane) {
        Launcher.currencyParentPane = currencyParentPane;
    }

    public static ArrayList<Currency> getCurrencyList() {
        return currencyList;
    }

    public static void setCurrencyList(ArrayList<Currency> currencyList) {
        Launcher.currencyList = currencyList;
    }
}
