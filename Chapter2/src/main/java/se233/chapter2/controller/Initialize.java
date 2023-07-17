package se233.chapter2.controller;

import se233.chapter2.model.Currency;
import se233.chapter2.model.CurrencyEntity;

import java.util.ArrayList;

public class Initialize {
    public static ArrayList<Currency> initialize_app(){
        Currency c = new Currency("USD");

        //set historical days
        ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getShortCode(),8);
        c.setHistorical(c_list);
        c.setCurrent(c_list.get(c_list.size()-1));
        ArrayList<Currency> currencyList = new ArrayList<>();
        currencyList.add(c);
        return currencyList;
    }
}
