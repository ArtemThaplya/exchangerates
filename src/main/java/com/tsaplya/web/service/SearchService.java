package com.tsaplya.web.service;

import com.tsaplya.web.model.Journal;
import org.springframework.stereotype.Component;

@Component
public class SearchService {
    private final SearchEntriesByCodeAndDate searchEntriesByCodeAndDate;

    public SearchService(SearchEntriesByCodeAndDate searchEntriesByCodeAndDate) {
        this.searchEntriesByCodeAndDate = searchEntriesByCodeAndDate;
    }

    public Journal getRateBuyAndSale(int currencyCode) {
        return searchEntriesByCodeAndDate.searchForJournalEntriesByCodeAndCurrentDate(currencyCode);
    }
}
