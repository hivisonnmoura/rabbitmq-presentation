package br.com.hivison.rabbit.basic.producer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

@JsonSerialize
public record Contact(
        String name,
        @JsonSerialize(using = LocalDateSerializer.class) LocalDate birthday,
        String phoneNumber
) {
}
