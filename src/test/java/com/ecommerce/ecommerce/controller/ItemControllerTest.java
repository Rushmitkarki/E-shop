package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.ItemDto;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.repo.ItemRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest{
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ItemRepo itemRepo;


    @InjectMocks
    private ItemController itemController;

    ItemDto itemDto1 = new ItemDto(null,"item1",1,1,"test",1,null,null);
    ItemDto itemDto2 = new ItemDto(null,"item2",1,1,"test",1,null,null);
    ItemDto itemDto3 = new ItemDto(null,"item3",1,1,"test",1,null,null);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(itemController).build();
    }

    @Test
    public void testAddItem_success() throws Exception {
        Item item = Item.builder()
                .itemId(1)
                .itemName("item1")
                .itemPrice(1)
                .itemQuantity(1)
                .itemDescription("test")
                .itemImage(null)
                .category(null)
                .user(null)
                .build();

        Mockito.when(itemRepo.save(item)).thenReturn(item);

        String content = objectWriter.writeValueAsString(item);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/seller/item/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest).
                andExpect(status().isOk())
                .andExpect(jsonPath("S", notNullValue()));
    }






}