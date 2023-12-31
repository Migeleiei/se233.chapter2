package se233.chapter2.controller;

import javafx.scene.control.*;
import org.json.JSONException;
import se233.chapter2.Launcher;
import se233.chapter2.model.Currency;
import se233.chapter2.model.CurrencyEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


public class AllEventHandlers {

    public static void onRefresh() {
        try {
            Launcher.refreshPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onAdd() {
        boolean isDone = true;
        while(isDone = true) {
            try {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Currency");
                dialog.setContentText("Currency code:");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> code = dialog.showAndWait();
                if (code.isPresent()) {
                    ArrayList<Currency> currency_list = Launcher.getCurrencyList();
                    Currency c = new Currency(code.get().toUpperCase());
                    ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getShortCode(), 8);
                    c.setHistorical(c_list);
                    c.setCurrent(c_list.get(c_list.size() - 1));
                    currency_list.add(c);
                    Launcher.setCurrencyList(currency_list);
                    Launcher.refreshPane();
                }
                isDone = false;

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("You input wrong Currency code");
                alert.showAndWait();
            }
        }
    }

    public static void onDelete(String code) {
        try {
            ArrayList<Currency> currency_list = Launcher.getCurrencyList();
            int index = -1;
            for (int i = 0; i < currency_list.size(); i++) {
                if (currency_list.get(i).getShortCode().equals(code)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                currency_list.remove(index);
                Launcher.setCurrencyList(currency_list);
                Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void onWatch(String code) {
        try {
            ArrayList<Currency> currency_list = Launcher.getCurrencyList();
            int index = -1;
            for (int i = 0; i < currency_list.size(); i++) {
                if (currency_list.get(i).getShortCode().equals(code)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Watch");
                dialog.setContentText("Rate:");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> retrievedRate = dialog.showAndWait();
                // add && !retrievedRate.get().trim().equals("") for protect error
                //

                if (retrievedRate.isPresent() && !retrievedRate.get().trim().equals("")) {
                    double rate = Double.parseDouble(retrievedRate.get());
                    currency_list.get(index).setWatch(true);
                    currency_list.get(index).setWatchRate(rate);
                    Launcher.setCurrencyList(currency_list);
                    Launcher.refreshPane();
                }
                Launcher.setCurrencyList(currency_list);
                Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void onUnWatch(String code) {

        ArrayList<Currency> currency_list = Launcher.getCurrencyList();
        try {
            int index = -1;
            for (int i = 0; i < currency_list.size(); i++) {
                if (currency_list.get(i).getShortCode().equals(code)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                // set false for not show
                currency_list.get(index).setWatch(false);
                Launcher.setCurrencyList(currency_list);
                Launcher.refreshPane();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

