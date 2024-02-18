package com.vahner.airticketsapp.controller.rest;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.dto.TicketDto;
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
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountById(@Uuid @PathVariable("uuid") String uuid) {
        return accountService.getAccountById(uuid);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.getAccounts());
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
    public ResponseEntity<String> deleteAccount(@PathVariable String uuid) {
        accountService.deleteAccount(uuid);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/{uuid}/purchase-tickets")
    public ResponseEntity<Void> purchaseTickets(@PathVariable String uuid) {
        accountService.purchaseTickets(uuid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{uuid}/addToCart")
    public ResponseEntity<String> addToCart(@PathVariable String uuid, @RequestBody TicketDto ticketDto) {
        accountService.addToCart(uuid, ticketDto);
        return new ResponseEntity<>("Ticket successfully added to cart", HttpStatus.OK);
    }

    @PostMapping("/{uuid}/removeFromCart")
    public ResponseEntity<String> removeFromCart(@PathVariable String uuid, @RequestBody TicketDto ticketDto) {
        accountService.removeFormCart(uuid, ticketDto);
        return new ResponseEntity<>("Ticket successfully returned from cart", HttpStatus.OK);
    }
}