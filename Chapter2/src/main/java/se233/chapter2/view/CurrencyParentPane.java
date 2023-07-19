package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se233.chapter2.model.Currency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CurrencyParentPane extends FlowPane {
    public CurrencyParentPane(ArrayList<Currency> currencyList) throws
            ExecutionException, InterruptedException {
        this.setPadding(new Insets(0));
        refreshPane(currencyList);
    }
    public void refreshPane(ArrayList<Currency> currencyList) throws
            ExecutionException, InterruptedException {
        this.getChildren().clear();
        for(int i=0 ; i<currencyList.size() ; i++) {
            FutureTask cp = new FutureTask(new CurrencyPane(currencyList.get(i)));
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(cp);
            Node node = (Node) cp.get();
            this.getChildren().add(node);
        }
    }
}
