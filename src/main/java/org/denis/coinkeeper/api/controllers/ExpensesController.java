package org.denis.coinkeeper.api.controllers;

import lombok.RequiredArgsConstructor;
import org.denis.coinkeeper.api.Services.ExpensesService;
import org.denis.coinkeeper.api.dto.ExpensesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExpensesController {

    public static final String CREATE_EXPENSES_API_ENDPOINT = "/api/expenses/new";
    public static final String EXPENSES_API_ENDPOINT = "/api/expenses/{id}";
    public static final String ALL_EXPENSES_API_ENDPOINT = "/api/expenses";
    public static final String USER_API_ENDPOINT = "/api/user";

    private final ExpensesService expensesService;


    @PostMapping(CREATE_EXPENSES_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> createExpenses(@RequestBody ExpensesDto expensesDto,
                                           Authentication authorization) {
        String email = authorization.getName();

        expensesService.createExpenses(expensesDto,email);

        return ResponseEntity
                .created(URI.create(USER_API_ENDPOINT))
                .build();
    }
    @GetMapping(ALL_EXPENSES_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ExpensesDto>> getAllExpenses(Authentication authorization) {
        String email = authorization.getName();

        return ResponseEntity
                .ok(expensesService.getAllExpenses(email));

    }

    @PutMapping(EXPENSES_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ExpensesDto> putExpenses(@RequestBody ExpensesDto expensesDto,
                                                 @PathVariable("id") Long expensesId,
                                                 Authentication authorization) {
        String email = authorization.getName();

        ExpensesDto expensesDtoResult = expensesService.putExpenses(expensesId,expensesDto,email);

        return ResponseEntity
                .ok(expensesDtoResult);

    }

    @GetMapping(EXPENSES_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ExpensesDto> getExpenses(@PathVariable("id") Long expensesId,
                                               Authentication authorization) {
        String email = authorization.getName();

        return ResponseEntity
                .ok(expensesService.getExpensesById(expensesId,email));

    }
    @DeleteMapping(EXPENSES_API_ENDPOINT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> removeExpenses(@PathVariable("id") Long expensesId,
                                          Authentication authorization) {
        String email = authorization.getName();
        expensesService.removeExpensesById(expensesId,email);

        return ResponseEntity
                .noContent()
                .build();

    }
}
