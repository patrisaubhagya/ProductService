package com.saubhagya.projectservice.services;

import com.saubhagya.projectservice.dto.FakeStoreRequestDTO;
import com.saubhagya.projectservice.exceptions.ProductNotFoundException;
import com.saubhagya.projectservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getProductById(String id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO);
}
