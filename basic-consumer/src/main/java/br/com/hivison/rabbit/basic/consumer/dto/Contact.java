package br.com.hivison.rabbit.basic.consumer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

@JsonSerialize
public record Contact(
        String name,
        String country
) {
}
