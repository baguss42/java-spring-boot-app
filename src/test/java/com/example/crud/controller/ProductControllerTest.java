package com.example.crud.controller;

import com.example.crud.model.Product;
import com.example.crud.repository.ProductRepository;
import com.example.crud.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testProductList() throws Exception {
        List<Product> products = new ArrayList<>();
        Product p1 = new Product(1, "test", 200, 300, true);
        products.add(p1);

        when(productService.ProductList()).thenReturn(products);

        mockMvc.perform(get("/product/all")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$.*").isNotEmpty()).
                andExpect(jsonPath("$.*").hasJsonPath()).
                andExpect(jsonPath("$.*").isArray()).
                andExpect(jsonPath("$.*", hasSize(products.size()))).
                andExpect(jsonPath("$.*",
                        containsInAnyOrder(products.get(0).getName())));
    }

//    @Test
//    void createProduct() {
//
//    }
//
//    @Test
//    void updateProduct() {
//    }
//
//    @Test
//    void deleteProduct() {
//    }
//
//    @Test
//    void patchProduct() {
//    }
}