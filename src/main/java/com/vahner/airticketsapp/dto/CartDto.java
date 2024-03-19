package com.vahner.airticketsapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    String id;
    List<TicketDto> tickets;
}