package com.tsaplya.web.service.Implementation;

import com.tsaplya.web.dao.JournalDao;
import com.tsaplya.web.model.Journal;
import com.tsaplya.web.service.Interfaces.CurrencyRateService;
import com.tsaplya.web.service.Interfaces.SearchEntriesByCodeAndDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;


@Component
public class SearchEntriesByCodeAndDateImp implements SearchEntriesByCodeAndDate {
    private static final Calendar calendar = Calendar.getInstance();
    private static final Calendar calendarCurrent = Calendar.getInstance();
    private static final String FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
    private final static Logger LOGGER = LoggerFactory.getLogger(SearchEntriesByCodeAndDateImp.class);

    private final JournalDao journalDao;
    private final CurrencyRateService currencyRateService;

    @Autowired
    public SearchEntriesByCodeAndDateImp(JournalDao journalDao, CurrencyRateService currencyRateService) {
        this.journalDao = journalDao;
        this.currencyRateService = currencyRateService;
    }

    /**
     * Поиск записи в журнале по коду и текущей дате. Если запись есть - возвращаем её в ответе.
     * Если записи нет в журнале - запрос курса через сторонний http сервис.
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
                return journal;
            }
        }
//        LOGGER.info("Валюта не найдена в базе данных. Запрашиваем удаленный сервер.");
        Journal currencyRate = currencyRateService.getCurrencyRate(currencyCode);
        journalDao.save(currencyRate);
        return currencyRate;
    }
}
