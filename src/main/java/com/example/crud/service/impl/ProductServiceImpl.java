package com.example.crud.service.impl;

import com.example.crud.dto.ProductDTO;
import com.example.crud.model.Product;
import com.example.crud.repository.ProductRepository;
import com.example.crud.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> ProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product CreateProduct(Product request) {
        request.setIsActive(false);
        return productRepository.save(request);
    }

    @Override
    public Boolean UpdateProduct(Long id, Product request) {
        Product result = ProductDetail(id);
        if (result != null) {
            result.setName(request.getName());
            result.setPrice(request.getPrice());
            result.setQty(request.getQty());
            productRepository.save(result);
            return true;
        }
        return false;
    }

    @Override
    public Boolean DeleteProduct(Long id) {
        Product result = ProductDetail(id);
        if (result != null) {
            productRepository.delete(result);
            return true;
        }
        return false;
    }

    @Override
    public Boolean PatchProduct(Long id, Boolean status) {
        Product result = ProductDetail(id);
        if (result != null) {
            result.setIsActive(status);
            productRepository.save(result);
            return true;
        }
        return false;
    }

    @Override
    public Product ProductDetail(Long id) {
        Optional<Product> result = productRepository.findById(id);
        return result.orElse(null);
    }

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public ProductDTO mapToDto(Product product) {
        return mapper.convertValue(product, ProductDTO.class);
    }

    @Override
    public Product mapToEntity(ProductDTO product) {
        return mapper.convertValue(product, Product.class);
    }
}
