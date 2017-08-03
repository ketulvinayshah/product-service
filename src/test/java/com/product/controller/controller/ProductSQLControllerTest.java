package com.product.controller.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by kshah on 7/31/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductSQLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllProducts() throws Exception {
//        this.mockMvc.perform(get("v1/products")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("toothbrush")));
    }
}