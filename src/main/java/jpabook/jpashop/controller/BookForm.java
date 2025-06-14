package jpabook.jpashop.controller;

public record BookForm(
        Long id,
        String name,
        int price,
        int stockQuantity,
        String author,
        String isbn
) {

    public BookForm() {
        this(null, null, 0, 0, null, null);
    }
}
