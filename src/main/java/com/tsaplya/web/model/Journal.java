package com.tsaplya.web.model;

import java.math.BigDecimal;


public class Journal {
    private int id;
    private String date;
    private int currencyCode;
    private BigDecimal rateBuy;
    private BigDecimal rateSell;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(int currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(BigDecimal rateBuy) {
        this.rateBuy = rateBuy;
    }

    public BigDecimal getRateSell() {
        return rateSell;
    }

    public void setRateSell(BigDecimal rateSell) {
        this.rateSell = rateSell;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "date='" + date + '\'' +
                ", currencyCode=" + currencyCode +
                ", rateBuy=" + rateBuy +
                ", rateSell=" + rateSell +
                '}';
    }
}
