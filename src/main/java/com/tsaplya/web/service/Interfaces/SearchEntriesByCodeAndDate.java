package com.tsaplya.web.service.Interfaces;

import com.tsaplya.web.model.Journal;

public interface SearchEntriesByCodeAndDate {
    Journal searchForJournalEntriesByCodeAndCurrentDate(int currencyCode);
}
