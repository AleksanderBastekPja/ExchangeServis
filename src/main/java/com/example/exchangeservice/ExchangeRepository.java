package com.example.exchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Currency, Long> { }
