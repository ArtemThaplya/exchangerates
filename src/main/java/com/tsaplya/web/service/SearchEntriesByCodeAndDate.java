package com.tsaplya.web.service;

import com.tsaplya.web.model.Journal;

import java.text.ParseException;

public interface SearchEntriesByCodeAndDate {
    Journal searchForJournalEntriesByCodeAndCurrentDate(int currencyCode) throws ParseException;
}
