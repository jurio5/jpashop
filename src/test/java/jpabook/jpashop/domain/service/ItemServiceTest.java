package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    void 아이템_저장() {
        // given
        Book book = new Book();
        book.setAuthor("김영한");
        book.setIsbn("123456789");

        // when
        itemService.saveItem(book);

        // then
        assertThat(book.getAuthor()).isEqualTo("김영한");
        assertThat(book.getIsbn()).isEqualTo("123456789");
    }

    @Test
    void 상품_다건_조회() {
        // given
        Book book1 = new Book();
        book1.setAuthor("김영한");
        book1.setIsbn("123456789");

        Book book2 = new Book();
        book2.setAuthor("박영준");
        book2.setIsbn("789456123");

        // when
        itemService.saveItem(book1);
        itemService.saveItem(book2);

        // then
        assertThat(itemService.findItems()).hasSize(2);
    }

    @Test
    void 상품_단건_조회() {
        // given
        Book book = new Book();
        book.setAuthor("김영한");
        book.setIsbn("1234567890");

        // when
        itemService.saveItem(book);

        // then
        assertThat(itemService.findOne(book.getId())).isEqualTo(book);
    }

}