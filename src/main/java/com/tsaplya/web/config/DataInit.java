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
import java.net.URL;
import java.net.URLConnection;

import static com.tsaplya.web.model.Mnemonics.EUR;
import static com.tsaplya.web.model.Mnemonics.USD;

@Component
public class DataInit implements ApplicationRunner {
    private final static int MNEMONICS_USD = 840;
    private final static int MNEMONICS_EUR = 978;
    private final static String DESCRIPTION_USD = "ДОЛЛАР США";
    private final static String DESCRIPTION_EUR = "ЕВРО";
    private JournalDao journalDao;
    private CurrencyReferenceDao currencyReferenceDao;

    @Autowired
    public DataInit(JournalDao journalDao, CurrencyReferenceDao currencyReferenceDao) {
        this.journalDao = journalDao;
        this.currencyReferenceDao = currencyReferenceDao;
    }

    @Override
    public void run(ApplicationArguments args) {
        long countCurrencyReference = currencyReferenceDao.count();
        long countJournal = journalDao.count();
        if (countCurrencyReference == 0) {
            CurrencyReference currencyReference = new CurrencyReference();
            currencyReference.setCurrencyCode(MNEMONICS_USD);
            currencyReference.setMnemonics(USD);
            currencyReference.setDescription(DESCRIPTION_USD);

            CurrencyReference currencyReference2 = new CurrencyReference();
            currencyReference2.setCurrencyCode(MNEMONICS_EUR);
            currencyReference2.setMnemonics(EUR);
            currencyReference2.setDescription(DESCRIPTION_EUR);

            currencyReferenceDao.save(currencyReference);
            currencyReferenceDao.save(currencyReference2);
        }

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
