package com.tsaplya.web.service.Implementation;

import com.tsaplya.web.model.CurrencyNotFoundException;
import com.tsaplya.web.model.Journal;
import com.tsaplya.web.service.Interfaces.CurrencyRateService;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CurrencyRateServiceImp implements CurrencyRateService {
    private final static String MONOBANK_URL = "https://api.monobank.ua/bank/currency";
    private final static Logger LOGGER = LoggerFactory.getLogger(CurrencyRateServiceImp.class);

    private final CurrencyParser currencyParser;

    @Autowired
    public CurrencyRateServiceImp(CurrencyParser currencyParser) {
        this.currencyParser = currencyParser;
    }

    /**
     * Запрашиваем и сохраняем курсы валют в журнал (доллар и евро по отношению к гривне).
     *
     * @return возвращает текущий курс валют.
     * @param currencyCode код валюты для поиска.
     */
    public Journal getCurrencyRate(int currencyCode) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(MONOBANK_URL);
        request.addHeader("accept", "application/json");
        try {
            HttpResponse response = client.execute(request);
            String json = IOUtils.toString(response.getEntity().getContent());
            return currencyParser.createJournal(json, currencyCode);
        } catch (IOException e) {
            LOGGER.error("Не удалось получить курс валют от удаленного сервера!");
            throw new CurrencyNotFoundException("Не удалось получить курс валют от удаленного сервера!");
        }
    }
}
