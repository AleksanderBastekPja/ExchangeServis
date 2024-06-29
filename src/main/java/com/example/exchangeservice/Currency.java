package com.example.exchangeservice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id of currency rate record.", required = true)
    private Integer id;

    @Schema(description = "Symbol of currency", required = true)
    private String currencyCode;

    @Schema(description = "Sum of rates divided by amount of days", required = true)
    private Double averageRate;

    @Schema(description = "Amount of days used to compute average rate", required = true)
    private Integer amountOfDays;

    @Schema(description = "Date and time of request", required = true)
    private Instant created;

    public Currency() {}

    public Currency(Integer id, String currencyCode, Double averageRate, Integer amountOfDays, Instant created) {
        this.id = id;
        this.currencyCode = currencyCode;
        this.averageRate = averageRate;
        this.amountOfDays = amountOfDays;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public Integer getAmountOfDays() {
        return amountOfDays;
    }

    public void setAmountOfDays(Integer amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
}
