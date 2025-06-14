package jpabook.jpashop.controller;

import jpabook.jpashop.domain.dto.UpdateItemDto;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.name());
        book.setPrice(form.price());
        book.setStockQuantity(form.stockQuantity());
        book.setAuthor(form.author());
        book.setIsbn(form.isbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getStockQuantity(),
                item.getAuthor(),
                item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

//    @PostMapping("/items/{itemId}/edit")
    public String updateItemV1(@ModelAttribute("form") BookForm form) {

        Book book = new Book();
        book.setId(form.id());
        book.setName(form.name());
        book.setPrice(form.price());
        book.setStockQuantity(form.stockQuantity());
        book.setAuthor(form.author());
        book.setIsbn(form.isbn());

        itemService.saveItem(book);

        return "redirect:/items";
    }

//    @PostMapping("/items/{itemId}/edit")
    public String updateItemV2(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {
        itemService.updateItemV2(itemId, form.name(), form.price(), form.stockQuantity());
        return "redirect:/items";
    }

    /**
     * 파라미터가 많아질 경우
     */
//    @PostMapping("/items/{itemId}/edit")
    public String updateItemV3(@PathVariable Long itemId, @ModelAttribute("form") UpdateItemDto updateItemDto) {
        itemService.updateItemV3(itemId, updateItemDto);
        return "redirect:/items";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItemV4(@PathVariable Long itemId, @ModelAttribute("form") UpdateItemDto updateItemDto) {
        itemService.updateItemV4(itemId, updateItemDto);
        return "redirect:/items";
    }
}
