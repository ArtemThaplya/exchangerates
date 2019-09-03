package com.tsaplya.web.service.Implementation;

import com.tsaplya.web.model.CurrencyNotFoundException;
import com.tsaplya.web.model.Journal;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CurrencyParser {
    private final static int CURRENCY_CODE_UAH = 980;

    Journal createJournal(String json, int currencyCode) throws CurrencyNotFoundException {
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            if ((Integer) object.get("currencyCodeA") == currencyCode
                    && (Integer) object.get("currencyCodeB") == CURRENCY_CODE_UAH) {
                Journal journal = new Journal();
                journal.setCurrencyCode((Integer) object.get("currencyCodeA"));
                journal.setDate(String.valueOf(object.get("date")));
                journal.setRateBuy((Double) object.get("rateBuy"));
                journal.setRateSell((Double) object.get("rateSell"));
                return journal;
            }
        }
        throw new CurrencyNotFoundException("Валюта не поддерживается!");
    }
}

