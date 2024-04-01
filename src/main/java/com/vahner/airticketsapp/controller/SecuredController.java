package com.vahner.airticketsapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Tag(name = "Secured Controller")
@RestController
@RequestMapping("/api")
public class SecuredController {

    @GetMapping("/secured/account")
    public String accountAccess(Principal principal){
        if (principal == null)
            return null;
        return principal.getName();
    }
}
