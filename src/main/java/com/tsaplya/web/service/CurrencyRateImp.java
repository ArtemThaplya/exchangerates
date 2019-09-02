package com.tsaplya.web.service;

import com.tsaplya.web.dao.JournalDao;
import com.tsaplya.web.model.Journal;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CurrencyRateImp implements CurrencyRate {
    private final static String MONOBANK_URL = "https://api.monobank.ua/bank/currency";
    private final static int CURRENCY_CODE_USD = 840;
    private final static int CURRENCY_CODE_EUR = 978;
    private final static int CURRENCY_CODE_UAH = 980;
    private final JournalDao journalDao;

    @Autowired
    public CurrencyRateImp(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    /*
    Запрашиваем и сохраняем курсы валют в журнал (доллар и евро по отношению к гривне).
     **/
    public void getCurrencyRate() throws IOException {
        long countJournal = journalDao.count();

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(MONOBANK_URL);
        request.addHeader("accept", "application/json");
        HttpResponse response = client.execute(request);
        String json = IOUtils.toString(response.getEntity().getContent());
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);

            if (countJournal == 0) {
                if ((Integer) object.get("currencyCodeA") == CURRENCY_CODE_USD
                        | (Integer) object.get("currencyCodeA") == CURRENCY_CODE_EUR
                        & (Integer) object.get("currencyCodeB") == CURRENCY_CODE_UAH) {
                    Journal journal = new Journal();
                    journal.setCurrencyCode((Integer) object.get("currencyCodeA"));
                    journal.setDate(String.valueOf(object.get("date")));
                    journal.setRateBuy((Double) object.get("rateBuy"));
                    journal.setRateSell((Double) object.get("rateSell"));
                    journalDao.save(journal);
                }
            }
        }
    }
}
