package com.example.crud.dto;

public record ProductDTO(Long id, String name, Long price, Integer qty, Boolean isActive) {
}
