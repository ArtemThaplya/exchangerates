package com.tsaplya.web.model;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Configuration
@Table(name = "Journal")
public class Journal {
    @Id
    @NotNull
    @Positive
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "date")
    private String date;

    @NotNull
    @Positive
    @Column(name = "currencyCode")
    private int currencyCode;

    @NotNull
    @Column(name = "rateBuy")
    private BigDecimal rateBuy;

    @NotNull
    @Column(name = "rateSell")
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
