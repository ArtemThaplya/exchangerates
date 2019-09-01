package com.tsaplya.web.service;

import com.tsaplya.web.dao.JournalDao;
import com.tsaplya.web.model.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
public class SearchEntriesByCodeAndDateImp implements SearchEntriesByCodeAndDate {
    private final JournalDao journalDao;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private Journal result = new Journal();

    @Autowired
    public SearchEntriesByCodeAndDateImp(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    public Journal searchForJournalEntriesByCodeAndCurrentDate(int currencyCode) {
        Iterable<Journal> all = journalDao.findAll();
        for (Journal journal : all) {
            if (journal.getCurrencyCode() == currencyCode & sdf.format(journal.getDate()).equals(sdf.format(timestamp))) {
                result = journal;
            }
        }
        return result;
    }
}
