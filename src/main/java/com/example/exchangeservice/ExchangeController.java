package com.example.exchangeservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ExchangeController {
    ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

//    @PostMapping("/currency/{code}")
//    ResponseEntity<Currency> postCurrency(@PathVariable("code") String code, @RequestBody Integer amountOfDays) {
//        Currency fetchedCurrency = exchangeService.getCurrency(code, amountOfDays);
//        return ResponseEntity.ok(fetchedCurrency);
//    }

    @Operation(summary = "Get average currency rate from passed days amount and save result in db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Currency average rate retrieved Successfully"),
            @ApiResponse(responseCode = "400", description = "Wrong data was passed or days amount limit exceeded.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Currency not found.", content = @Content)
    })
    @GetMapping("/currency/{code}")
    public ResponseEntity<Currency> getCurrency(@PathVariable("code") String code, @RequestBody @Nullable Integer days) {
        return ResponseEntity.ok(exchangeService.getCurrency(code, days));
    }
}
