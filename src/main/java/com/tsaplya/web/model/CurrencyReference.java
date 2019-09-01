package com.tsaplya.web.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Configuration
@Table(name = "CurrencyReference")
public class CurrencyReference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "mnemonics")
    private Mnemonics mnemonics;

    @NotNull
    @Column(name = "currencyCode")
    private int currencyCode;

    @Column(name = "description")
    private String description;

    @Bean
    CurrencyReference create() {
        return new CurrencyReference();
    }

    public Mnemonics getMnemonics() {
        return mnemonics;
    }

    public void setMnemonics(Mnemonics mnemonics) {
        this.mnemonics = mnemonics;
    }

    public int getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(int currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CurrencyReference{" +
                "mnemonics='" + mnemonics + '\'' +
                ", currencyCode=" + currencyCode +
                ", description='" + description + '\'' +
                '}';
    }
}
