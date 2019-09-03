package com.tsaplya.web.service;

import com.tsaplya.web.model.Journal;
import com.tsaplya.web.service.Interfaces.CodeCurrency;
import com.tsaplya.web.service.Interfaces.SearchEntriesByCodeAndDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


@Component
public class SearchService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    private final SearchEntriesByCodeAndDate searchEntriesByCodeAndDate;
    private final CodeCurrency codeCurrency;

    public SearchService(SearchEntriesByCodeAndDate searchEntriesByCodeAndDate, CodeCurrency codeCurrency) {
        this.searchEntriesByCodeAndDate = searchEntriesByCodeAndDate;
        this.codeCurrency = codeCurrency;
    }

    @Cacheable(value = "getRateBuyAndSale", key="#currencyCode")
    public Journal getRateBuyAndSale(Integer currencyCode) {
        LOGGER.info("looking for a rate");
        return searchEntriesByCodeAndDate.searchForJournalEntriesByCodeAndCurrentDate(currencyCode);
    }

    @Cacheable(value = "getCodeCurrencyFromReference", key="#mnemonics")
    public int getCodeCurrencyFromReference(String mnemonics) {
        LOGGER.info("looking for a ref");
        return codeCurrency.getCodeCurrency(mnemonics);
    }
}
