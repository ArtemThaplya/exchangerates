package com.tsaplya.web.service.Interfaces;

import com.tsaplya.web.model.Journal;

public interface CurrencyRateService {
    Journal getCurrencyRate(int currencyCode);
}
