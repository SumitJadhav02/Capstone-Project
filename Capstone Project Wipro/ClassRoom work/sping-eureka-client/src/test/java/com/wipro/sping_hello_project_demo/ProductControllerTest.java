package com.wipro.sping_hello_project_demo;

import com.wipro.model.Product;
import com.wipro.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Before
    public void init() {
        Product product = new Product(1, "Laptop", 9000.0);
        when(productService.getProductById(1)).thenReturn(Optional.of(product));
    }

    @Test
    public void find_productById_OK() throws Exception {
        mockMvc.perform(get("/helloapi/products/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.pname", is("Laptop")))
                .andExpect(jsonPath("$.price", is(9000.0)));

        verify(productService, times(1)).getProductById(1);
    }

    @Test
    public void find_productById_NotFound() throws Exception {
        mockMvc.perform(get("/helloapi/products/5"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void save_product_OK() throws Exception {
        Product newProduct = new Product(2, "Mobile", 5000.0);
        when(productService.createProduct(any(Product.class))).thenReturn(newProduct);

        mockMvc.perform(post("/helloapi/products")
                .content(om.writeValueAsString(newProduct))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.pname", is("Mobile")))
                .andExpect(jsonPath("$.price", is(5000.0)));

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    public void update_product_OK() throws Exception {
        Product updatedProduct = new Product(1, "Updated Laptop", 12000.0);
        when(productService.updateProduct(anyInt(), any(Product.class))).thenReturn(ResponseEntity.ok(updatedProduct));

        mockMvc.perform(put("/helloapi/products/1")
                .content(om.writeValueAsString(updatedProduct))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.pname", is("Updated Laptop")))
                .andExpect(jsonPath("$.price", is(12000.0)));
    }

    @Test
    public void delete_product_OK() throws Exception {
        when(productService.deleteProduct(anyInt())).thenReturn(Map.of("Product has been Deleted", true));

        mockMvc.perform(delete("/helloapi/products/1"))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(1);
    }
}
