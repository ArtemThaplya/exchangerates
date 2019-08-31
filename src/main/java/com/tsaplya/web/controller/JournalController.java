package com.tsaplya.web.controller;

import com.tsaplya.web.model.Journal;
import com.tsaplya.web.service.SearchService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class JournalController {
    private final SearchService searchService;

    public JournalController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/journal/{currencyCode}", method = GET)
    @ResponseBody
    public Journal get(@Valid @PathVariable("currencyCode") int currencyCode) {
        return searchService.getRateBuyAndSale(currencyCode);
    }
}
