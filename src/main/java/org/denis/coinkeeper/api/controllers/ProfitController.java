package org.denis.coinkeeper.api.controllers;

import lombok.RequiredArgsConstructor;
import org.denis.coinkeeper.api.Services.ProfitService;
import org.denis.coinkeeper.api.dto.ProfitDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfitController {

    public static final String CREATE_PROFIT_API_ENDPOINT = "/api/profit/new";
    public static final String PROFIT_API_ENDPOINT = "/api/profit/{id}";
    public static final String PROFITS_API_ENDPOINT = "/api/profits";
    public static final String USER_API_ENDPOINT = "/api/user";

    private final ProfitService profitService;


    @PostMapping(CREATE_PROFIT_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProfitDto> createProfit(@RequestBody ProfitDto profitDto,
                                           Authentication authorization) {
        String email = authorization.getName();

        profitService.createProfit(profitDto,email);

        return ResponseEntity
                .created(URI.create(USER_API_ENDPOINT))
                .build();
    }
    @GetMapping(PROFITS_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProfitDto>> getProfits(Authentication authorization) {
        String email = authorization.getName();

        return ResponseEntity
                .ok(profitService.getProfits(email));

    }

    @PatchMapping(PROFIT_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProfitDto> patchProfit(@RequestBody ProfitDto profitDto,
                                                 @PathVariable("id") Long profitId,
                                                 Authentication authorization) {
        String email = authorization.getName();

        ProfitDto profitDtoResult = profitService.patchProfit(profitId,profitDto,email);

        return ResponseEntity
                .ok(profitDtoResult);

    }

    @GetMapping(PROFIT_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProfitDto> getProfit(@PathVariable("id") Long profitId,
                                               Authentication authorization) {
        String email = authorization.getName();

        return ResponseEntity
                .ok(profitService.getProfitById(profitId,email));

    }
    @DeleteMapping(PROFIT_API_ENDPOINT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> removeProfit(@PathVariable("id") Long profitId,
                                               Authentication authorization) {
        String email = authorization.getName();
        profitService.removeProfitById(profitId,email);

        return ResponseEntity
                .noContent()
                .build();

    }
}
