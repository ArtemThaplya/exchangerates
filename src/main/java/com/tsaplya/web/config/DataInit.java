package com.tsaplya.web.config;

import com.tsaplya.web.dao.CurrencyReferenceDao;
import com.tsaplya.web.dao.JournalDao;
import com.tsaplya.web.model.CurrencyReference;
import com.tsaplya.web.model.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
    public void run(ApplicationArguments args) {
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

        if (countJournal == 0) {
            Journal journal = new Journal();
            journal.setCurrencyCode(840);
            journal.setDate("1567305609");
            journal.setRateBuy(BigDecimal.valueOf(24.961));
            journal.setRateSell(BigDecimal.valueOf(25.2417));

            Journal journal2 = new Journal();
            journal2.setCurrencyCode(978);
            journal2.setDate("1567305609");
            journal2.setRateBuy(BigDecimal.valueOf(27.511));
            journal2.setRateSell(BigDecimal.valueOf(28.0788));

            journalDao.save(journal);
            journalDao.save(journal2);
        }
    }
}
