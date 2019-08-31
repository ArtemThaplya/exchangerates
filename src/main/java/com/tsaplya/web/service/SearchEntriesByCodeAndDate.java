package com.tsaplya.web.service;

import com.tsaplya.web.model.Journal;

public interface SearchEntriesByCodeAndDate {
    Journal searchForJournalEntriesByCodeAndCurrentDate(int currencyCode);
}
