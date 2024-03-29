package com.tsaplya.web.controller;

import com.tsaplya.web.model.Journal;
import com.tsaplya.web.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class JournalController {
    private final SearchService searchService;

    @Autowired
    public JournalController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/journal", method = GET)
    public Journal get(@RequestParam(value = "currencyCode") String mnemonics) {
        int currencyCode = searchService.getCodeCurrencyFromReference(mnemonics);
        return searchService.getRateBuyAndSale(currencyCode);
    }
}
