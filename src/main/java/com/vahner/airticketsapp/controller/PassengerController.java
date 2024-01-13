package com.vahner.airticketsapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/passenger")
@RestController
@RequiredArgsConstructor
public class PassengerController {


}
