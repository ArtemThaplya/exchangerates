package com.tsaplya.web.config;

import com.tsaplya.web.dao.CurrencyReferenceDao;
import com.tsaplya.web.dao.JournalDao;
import com.tsaplya.web.model.CurrencyReference;
import com.tsaplya.web.model.Journal;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class DataInit implements ApplicationRunner {


    private JournalDao journalDao;
    private CurrencyReferenceDao currencyReferenceDao;

    @Autowired
    public DataInit(JournalDao journalDao, CurrencyReferenceDao currencyReferenceDao) {
        this.journalDao = journalDao;
        this.currencyReferenceDao = currencyReferenceDao;
    }

    @Override
    public void run(ApplicationArguments args)  {
        long countCurrencyReference = currencyReferenceDao.count();
        long countJournal = journalDao.count();
        if (countCurrencyReference == 0) {
            CurrencyReference currencyReference1 = new CurrencyReference();
            currencyReference1.setCurrencyCode(980);
            currencyReference1.setMnemonics("UAH");
            currencyReference1.setDescription("УКРАИНСКАЯ ГРИВНА");

            CurrencyReference currencyReference2 = new CurrencyReference();
            currencyReference2.setCurrencyCode(840);
            currencyReference2.setMnemonics("USD");
            currencyReference2.setDescription("ДОЛЛАР США");

            CurrencyReference currencyReference3 = new CurrencyReference();
            currencyReference3.setCurrencyCode(978);
            currencyReference3.setMnemonics("EUR");
            currencyReference3.setDescription("ЕВРО");

            CurrencyReference currencyReference4 = new CurrencyReference();
            currencyReference4.setCurrencyCode(156);
            currencyReference4.setMnemonics("CNY");
            currencyReference4.setDescription("ЮАНЬ ЖЕНЬМИНЬБИ (КИТАЙ)");

            currencyReferenceDao.save(currencyReference1);
            currencyReferenceDao.save(currencyReference2);
            currencyReferenceDao.save(currencyReference3);
            currencyReferenceDao.save(currencyReference4);
        }

//        JSONObject json = new JSONObject(IOUtils.toString(new URL("https://https://api.monobank.ua/bank/currency"), Charset.forName("UTF-8")));

        JSONParser parser = new JSONParser();

        try {
            URL oracle = new URL("https://https://api.monobank.ua/bank/currency");
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);

                for (Object o : a) {
                    JSONObject tutorials = (JSONObject) o;
                    if (countJournal == 0) {
                        Journal journal = new Journal();
                        journal.setCurrencyCode((int) tutorials.get("currencyCodeA"));
                        journal.setDate((String) tutorials.get("date"));
                        journal.setRateBuy((BigDecimal) tutorials.get("rateBuy"));
                        journal.setRateSell((BigDecimal) tutorials.get("rateSell"));
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
