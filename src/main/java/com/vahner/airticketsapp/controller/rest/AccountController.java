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
    public ResponseEntity<AccountDto> getAccountById(@Uuid @PathVariable("uuid") String uuid) {
        AccountDto accountDto = accountService.getAccountById(uuid);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        List<AccountDto> accountDto = accountService.getAccounts();
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccountDto = accountService.create(accountDto);
        return ResponseEntity.ok(createdAccountDto);
    }

    @PutMapping("/updateAccount/{uuid}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable UUID uuid, @RequestBody AccountDto accountDto) {
        AccountDto updatedAccountDto = accountService.updateAccount(uuid, accountDto);
        return ResponseEntity.ok(updatedAccountDto);
    }

    @DeleteMapping("/deleteAccount/{uuid}")
    public ResponseEntity<String> deleteAccount(@PathVariable UUID uuid) {
        accountService.deleteAccount(uuid);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }
}