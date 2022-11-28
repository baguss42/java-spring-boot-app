package com.example.crud.controller;

import com.example.crud.dto.ProductDTO;
import com.example.crud.model.Product;
import com.example.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all")
    public List<ProductDTO> ProductList() {
        return productService.ProductList().stream().map(product -> productService.mapToDto(product)).
                collect(Collectors.toList());
    }

    @PostMapping(path = "/create")
    public ProductDTO CreateProduct(@RequestBody ProductDTO request) {
        final Product product = productService.mapToEntity(request);
        final Product result = productService.CreateProduct(product);
        return productService.mapToDto(result);
    }

    @PutMapping(path = "/update/{id}")
    public Boolean UpdateProduct(@PathVariable Long id, @RequestBody ProductDTO request) {
        final Product product = productService.mapToEntity(request);
        return productService.UpdateProduct(id, product);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Boolean DeleteProduct(@PathVariable Long id) {
        return productService.DeleteProduct(id);
    }

    @PatchMapping(path = "/patch/{id}")
    public Boolean PatchProduct(@PathVariable Long id, @RequestParam Boolean status) {
        return productService.PatchProduct(id, status);
    }
}
