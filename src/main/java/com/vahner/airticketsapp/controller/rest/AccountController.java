package com.vahner.airticketsapp.controller.rest;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.service.interf.AccountService;
import com.vahner.airticketsapp.validation.interf.Uuid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Tag("Account Controller")
@Validated
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/get/{uuid}")
    @Operation(summary = "basic account get rest method by uuid",
            description = "returns account from database for given uuid",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Account found"),
                    @ApiResponse(responseCode = "404", description = "No account was found with this uuid")
            })
    public ResponseEntity<AccountDto> getAccountById(@Uuid @PathVariable String uuid) {
        AccountDto accountDto = accountService.getAccountByUUID(uuid);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "get all accounts",
            description = "Retrieve a list of all accounts")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of accounts", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountDto.class)))
    })
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountList = accountService.getAllAccounts();
        return ResponseEntity.ok(accountList);
    }

    @PostMapping("/createAccount")
    @Operation(summary = "create a new account",
            description = "creates a new account based on the provided information")
    @ApiResponse(responseCode = "201", description = "Successfully created the account", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountDto.class))
    })
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.create(accountDto);
        return new ResponseEntity<>(createdAccount,HttpStatus.CREATED);
    }

    @PutMapping("/updateAccount/{uuid}")
    @Operation(summary = "update account",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "creates a new account",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class)
                    )
            )
    )
    public ResponseEntity<String> updateAccount(@PathVariable ("uuid") String uuid , @RequestBody AccountDto accountDto) {
        accountService.updateAccount(uuid, accountDto);
        return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAccount/{uuid}")
    @Operation(summary = "delete account",
            description = "deletion of an account by the specified uuid",
            hidden = true)
    @ApiResponse(responseCode = "204", description = "Successfully deleted the account")
    public ResponseEntity<Void> deleteAccount(@Uuid @PathVariable String uuid) {
        accountService.deleteAccount(uuid);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/getAccountBalance/{uuid}")
    @Operation(summary = "get account balance",
            description = "retrieves the balance of the account based on the provided UUID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the account balance", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountDto.class))
    })
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable String uuid) {
        BigDecimal balance = accountService.getAccountBalance(uuid);
        return ResponseEntity.ok(balance);
    }
}