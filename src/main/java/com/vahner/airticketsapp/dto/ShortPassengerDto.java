package com.vahner.airticketsapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortPassengerDto {

    String lastName;

    String phone;
}
