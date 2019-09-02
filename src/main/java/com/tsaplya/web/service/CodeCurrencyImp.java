package com.tsaplya.web.service;

import com.tsaplya.web.dao.CurrencyReferenceDao;
import com.tsaplya.web.model.CurrencyReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeCurrencyImp implements CodeCurrency{
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusUpdaterService.class);
    private final CurrencyReferenceDao currencyReferenceDao;

    @Autowired
    public CodeCurrencyImp(CurrencyReferenceDao currencyReferenceDao) {
        this.currencyReferenceDao = currencyReferenceDao;
    }

    /*
    Проверка наличия переданной валюты в справочнике (если нет - ошибка).
     **/
    public int getCodeCurrency(String mnemonics) {
        int result = 0;
        Iterable<CurrencyReference> all = currencyReferenceDao.findAll();
        for (CurrencyReference currencyReference : all) {
            if (String.valueOf(currencyReference.getMnemonics()).equals(mnemonics)) {
                result = currencyReference.getCurrencyCode();
                break;
            } else {
                LOGGER.info("Checking the presence of the transferred currency in the directory ended in ERROR!!!");
            }
        }
        return result;
    }
}
