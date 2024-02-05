package com.vahner.airticketsapp.controller.rest;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.service.interf.AccountService;
import com.vahner.airticketsapp.validation.interf.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountById(@Uuid @PathVariable("uuid") String uuid) {
        return accountService.getAccountById(uuid);
    }

    @GetMapping
    public List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping("/createAccount")
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        return accountService.create(accountDto);
    }

    @PutMapping("/updateAccount/{uuid}")
    public AccountDto updateAccount(@PathVariable UUID uuid, @RequestBody AccountDto accountDto) {
        return accountService.updateAccount(uuid, accountDto);
    }

    @DeleteMapping("/deleteAccount/{uuid}")
    public ResponseEntity<String> deleteAccount(@PathVariable UUID uuid) {
        accountService.deleteAccount(uuid);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }
}