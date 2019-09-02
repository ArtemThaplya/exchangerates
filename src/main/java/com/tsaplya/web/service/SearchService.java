package com.tsaplya.web.service;

import com.tsaplya.web.model.Journal;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class SearchService {
    private final SearchEntriesByCodeAndDate searchEntriesByCodeAndDate;
    private final CurrencyRate currencyRate;
    private final CodeCurrency codeCurrency;

    public SearchService(SearchEntriesByCodeAndDate searchEntriesByCodeAndDate, CurrencyRate currencyRate, CodeCurrency codeCurrency) {
        this.searchEntriesByCodeAndDate = searchEntriesByCodeAndDate;
        this.currencyRate = currencyRate;
        this.codeCurrency = codeCurrency;
    }

    public Journal getRateBuyAndSale(int currencyCode) throws ParseException {
        return searchEntriesByCodeAndDate.searchForJournalEntriesByCodeAndCurrentDate(currencyCode);
    }

    public void getCurrencyRateMonobank() throws IOException {
        currencyRate.getCurrencyRate();
    }

    public int getCodeCurrencyFromReference(String mnemonics){
        return codeCurrency.getCodeCurrency(mnemonics);
    }
}
