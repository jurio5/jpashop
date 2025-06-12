package jpabook.jpashop.domain.dto;

public record UpdateItemDto(
        String name,
        int price,
        int stockQuantity
) {}
