package com.tsaplya.web.service;

import com.tsaplya.web.model.Journal;
import org.springframework.stereotype.Component;

@Component
public class SearchService {
    private final SearchEntriesByCodeAndDate searchEntriesByCodeAndDate;
    private final CurrencyRate currencyRate;

    public SearchService(SearchEntriesByCodeAndDate searchEntriesByCodeAndDate, CurrencyRate currencyRate) {
        this.searchEntriesByCodeAndDate = searchEntriesByCodeAndDate;
        this.currencyRate = currencyRate;
    }

    public Journal getRateBuyAndSale(int currencyCode) {
        return searchEntriesByCodeAndDate.searchForJournalEntriesByCodeAndCurrentDate(currencyCode);
    }

    public void getCurrencyRateMonobank() {
        currencyRate.getCurrencyRate();
    }
}
