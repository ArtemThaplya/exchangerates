package com.tsaplya.web.model;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Configuration
@Table(name = "CurrencyReference")
public class CurrencyReference {
    @Id
    @NotNull
    @Positive
    @Column(name = "requestId")
    private Mnemonics mnemonics;

    @NotNull
    @Positive
    @Column(name = "currencyCode")
    private int currencyCode;

    @Column(name = "description")
    private String description;

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
