package com.tsaplya.web.service.Implementation;

import com.tsaplya.web.dao.JournalDao;
import com.tsaplya.web.model.Journal;
import com.tsaplya.web.service.Interfaces.SearchEntriesByCodeAndDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;


@Component
public class SearchEntriesByCodeAndDateImp implements SearchEntriesByCodeAndDate {
    private final JournalDao journalDao;
    private Journal result = new Journal();
    private static final Calendar calendar = Calendar.getInstance();
    private static final Calendar calendarCurrent = Calendar.getInstance();
    private static final String FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);

    @Autowired
    public SearchEntriesByCodeAndDateImp(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    /**
     * Поиск записи в журнале по коду и текущей дате. Если запись есть - возвращаем её в ответе.
     */
    public Journal searchForJournalEntriesByCodeAndCurrentDate(int currencyCode) {
        Iterable<Journal> all = journalDao.findAll();
        for (Journal journal : all) {
            Date localDate = Date.valueOf(LocalDate.now());
            long getDateJournal = Long.parseLong(journal.getDate());
            Date date = new Date(getDateJournal * 1000);

            Date currencyRequestDate = Date.valueOf(simpleDateFormat.format(date));

            calendar.setTime(localDate);
            calendarCurrent.setTime(currencyRequestDate);

            if (journal.getCurrencyCode() == currencyCode
                    && calendar.get(Calendar.YEAR) == calendarCurrent.get(Calendar.YEAR)
                    && calendar.get(Calendar.MONTH) == calendarCurrent.get(Calendar.MONTH)
                    && calendar.get(Calendar.DATE) == calendarCurrent.get(Calendar.DATE)) {
                result = journal;
            }
        }
        return result;
    }
}
