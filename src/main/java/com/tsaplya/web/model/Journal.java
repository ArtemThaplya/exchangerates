package com.tsaplya.web.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Configuration
@Table(name = "Journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private String date;

    @Column(name = "currencyCode")
    private Long currencyCode;

    @Column(name = "rateBuy")
    private double rateBuy;

    @Column(name = "rateSell")
    private double rateSell;

    @Bean
    Journal create() {
        return new Journal();
    }

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

    public Long getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Long currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(double rateBuy) {
        this.rateBuy = rateBuy;
    }

    public double getRateSell() {
        return rateSell;
    }

    public void setRateSell(double rateSell) {
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
