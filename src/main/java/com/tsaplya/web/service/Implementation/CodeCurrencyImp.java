package com.tsaplya.web.service.Implementation;

import com.tsaplya.web.dao.CurrencyReferenceDao;
import com.tsaplya.web.model.CurrencyReference;
import com.tsaplya.web.service.Interfaces.CodeCurrency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.tsaplya.web.model.Mnemonics.EUR;
import static com.tsaplya.web.model.Mnemonics.USD;

@Component
public class CodeCurrencyImp implements CodeCurrency {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeCurrencyImp.class);

    private final CurrencyReferenceDao currencyReferenceDao;

    @Autowired
    public CodeCurrencyImp(CurrencyReferenceDao currencyReferenceDao) {
        this.currencyReferenceDao = currencyReferenceDao;
    }

    /**
     * Проверка наличия переданной валюты в справочнике (если нет - ошибка).
     */
    public int getCodeCurrency(String mnemonics) {
        int result = 0;
        Iterable<CurrencyReference> all = currencyReferenceDao.findAll();
        for (CurrencyReference currencyReference : all) {
            if (String.valueOf(currencyReference.getMnemonics()).equals(mnemonics)) {
                result = currencyReference.getCurrencyCode();
                break;
            } else if (!currencyReference.getMnemonics().equals(USD) & !currencyReference.getMnemonics().equals(EUR)) {
                LOGGER.info("Checking the presence of the transferred currency in the directory ended in ERROR!!!");
                break;
            }
        }
        return result;
    }
}
