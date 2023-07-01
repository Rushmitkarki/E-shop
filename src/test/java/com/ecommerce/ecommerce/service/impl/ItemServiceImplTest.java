package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemServiceImpl itemServiceImpl;

    @BeforeEach
    void setUp() {
        Optional<Item> item = Optional.of(new Item());
        item.get().setItemName("test");
        item.get().setItemId(1);
        item.get().setItemDescription("test");
        item.get().setItemPrice(1);
        item.get().setItemQuantity(1);
        item.get().setCategory(null);
        item.get().setUser(null);
        item.get().setItemImage(null);

        Mockito.when(itemServiceImpl.getByIdNoOpt(1)).thenReturn(item);
    }

    @Test
    void getByIdNoOpt() {
        String name = "test";
        assertEquals(name, itemService.getByIdNoOpt(1).get().getItemName());
    }

    @Test
    void deleteItem() {
        itemService.deleteItem(1);
        Mockito.verify(itemServiceImpl, Mockito.times(1)).deleteItem(1);
    }

}