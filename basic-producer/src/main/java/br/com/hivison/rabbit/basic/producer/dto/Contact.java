package br.com.hivison.rabbit.basic.producer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record Contact(
        String name,
        String country
) {
}
