package com.example.exchangeservice;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Objects;

import static java.util.Objects.requireNonNullElse;

@Service
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final RestTemplate restTemplate;

    public ExchangeService(ExchangeRepository exchangeRepository, RestTemplate restTemplate) {
        this.exchangeRepository = exchangeRepository;
        this.restTemplate = restTemplate;
    }

    public Currency getAverageLastDaysRate(String currencyCode, @Nullable Integer amountOfDays) {
        Integer days = requireNonNullElse(amountOfDays, 1);
        CurrencyNBP lastDaysRates = restTemplate.getForObject("https://api.nbp.pl/api/exchangerates/rates/A/" + currencyCode + "/last/" + days + "?format=json", CurrencyNBP.class);
        Double averageRate = lastDaysRates.getRates().stream().map(Rate::getMid).reduce(0D, Double::sum) / days;

        return new Currency(null, currencyCode, averageRate, days, Instant.now());
    }

//    private Currency getCurrencyRate(String currencyCode, Integer amountOfDays) {
//        return restTemplate.getForObject("http://localhost:8081/currency/" + currencyCode + "/" + amountOfDays, Currency.class);
//    }

    public Currency getCurrency(String currencyCode, @Nullable Integer amountOfDay) {
        Currency currency = getAverageLastDaysRate(currencyCode, amountOfDay);
        exchangeRepository.save(currency);
        return currency;
    }
}
