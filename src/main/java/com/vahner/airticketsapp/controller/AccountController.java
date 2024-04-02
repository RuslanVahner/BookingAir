package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.dto.ShortAccountDto;
import com.vahner.airticketsapp.dto.SignupRequest;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.enums.Role;
import com.vahner.airticketsapp.service.interf.AccountService;
import com.vahner.airticketsapp.validation.interf.Uuid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Tag(name = "Account Controller")
@Validated
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private ValidationErrors bindingResult;

    @GetMapping("/get/{id}")
    @Operation(summary = "basic account get rest method by id",
            description = "returns account from database for given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Account found"),
                    @ApiResponse(responseCode = "404", description = "No account was found with this id")
            })
    public ResponseEntity<AccountDto> getAccountById(@Uuid @PathVariable String id) {
        AccountDto accountDto = accountService.getAccountWithCartById(id);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "get all accounts",
            description = "Retrieve a list of all accounts")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of accounts", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountDto.class)))
    })
    public ResponseEntity<List<ShortAccountDto>> getAllShortAccounts() {
        List<ShortAccountDto> accountList = accountService.getAllShortAccounts();
        return ResponseEntity.ok(accountList);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
        }
        if (accountService.existsByLogin(signupRequest.getLogin())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different login");
        }
       Account account = new Account();
        account.setLogin(signupRequest.getLogin());
        account.setBalance(BigDecimal.ZERO);
        account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        account.setCreateAccountDate(LocalDate.now());
        account.setOwner(signupRequest.getLogin());
        account.setRole(Collections.singleton(Role.PASSENGER));
        accountService.save(account);
        return ResponseEntity.ok("Account created");
    }


    @PutMapping("/updateAccount/{id}")
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
    public ResponseEntity<String> updateAccount(@PathVariable("id") String uuid, @RequestBody AccountDto accountDto) {
        accountService.updateAccount(uuid, accountDto);
        return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAccount/{id}")
    @Operation(summary = "delete account",
            description = "deletion of an account by the specified id",
            hidden = true)
    @ApiResponse(responseCode = "204", description = "Successfully deleted the account")
    public ResponseEntity<Void> deleteAccount(@Uuid @PathVariable String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAccountBalance/{id}")
    @Operation(summary = "get account balance",
            description = "retrieves the balance of the account based on the provided ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the account balance", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountDto.class))
    })
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable String id) {
        BigDecimal balance = accountService.getAccountBalance(id);
        return ResponseEntity.ok(balance);
    }
}