package com.example.crud.service;

import com.example.crud.dto.ProductDTO;
import com.example.crud.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> ProductList();
    Product CreateProduct(Product request);
    Boolean UpdateProduct(Long id, Product request);
    Boolean DeleteProduct(Long id);
    Boolean PatchProduct(Long id, Boolean status);

    Product ProductDetail(Long id);

    ProductDTO mapToDto(Product product);
    Product mapToEntity(ProductDTO product);
}
