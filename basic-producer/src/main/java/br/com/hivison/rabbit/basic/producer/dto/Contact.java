package br.com.hivison.rabbit.basic.producer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@JsonSerialize
public record Contact(
        String name,
        @JsonSerialize(using = LocalDateTimeSerializer.class) LocalDateTime  birthday,
        String phoneNumber
) {
}
