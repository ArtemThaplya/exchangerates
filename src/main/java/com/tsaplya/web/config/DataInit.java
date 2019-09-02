package com.tsaplya.web.config;

import com.tsaplya.web.dao.CurrencyReferenceDao;
import com.tsaplya.web.model.CurrencyReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static com.tsaplya.web.model.Mnemonics.EUR;
import static com.tsaplya.web.model.Mnemonics.USD;

@Component
public class DataInit implements ApplicationRunner {
    private final static int MNEMONICS_USD = 840;
    private final static int MNEMONICS_EUR = 978;
    private final static String DESCRIPTION_USD = "ДОЛЛАР США";
    private final static String DESCRIPTION_EUR = "ЕВРО";
    private CurrencyReferenceDao currencyReferenceDao;

    @Autowired
    public DataInit(CurrencyReferenceDao currencyReferenceDao) {
        this.currencyReferenceDao = currencyReferenceDao;
    }

    /**
     * Справочник валют (мнемоника, код, описание).
     */
    @Override
    public void run(ApplicationArguments args) {
        long countCurrencyReference = currencyReferenceDao.count();

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
    }
}
