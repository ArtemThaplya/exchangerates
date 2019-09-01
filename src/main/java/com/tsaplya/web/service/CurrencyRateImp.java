package com.tsaplya.web.service;

import com.tsaplya.web.dao.JournalDao;
import com.tsaplya.web.model.Journal;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;

@Component
public class CurrencyRateImp implements CurrencyRate {
    private final JournalDao journalDao;

    @Autowired
    public CurrencyRateImp(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    public void getCurrencyRate() {
        long countJournal = journalDao.count();
        JSONParser parser = new JSONParser();

        try {
            URL oracle = new URL("https://https://api.monobank.ua/bank/currency");
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);

                for (Object o : a) {
                    JSONObject jsonObject = (JSONObject) o;
                    if (countJournal == 0) {
                        Journal journal = new Journal();
                        journal.setCurrencyCode((int) jsonObject.get("currencyCodeA"));
                        journal.setDate((String) jsonObject.get("date"));
                        journal.setRateBuy((BigDecimal) jsonObject.get("rateBuy"));
                        journal.setRateSell((BigDecimal) jsonObject.get("rateSell"));
                        journalDao.save(journal);
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ignored) {
        }
    }
}
