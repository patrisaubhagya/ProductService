package com.saubhagya.projectservice.services;

import com.saubhagya.projectservice.dto.FakeStoreRequestDTO;
import com.saubhagya.projectservice.exceptions.DBNotFoundException;
import com.saubhagya.projectservice.exceptions.DBTImeoutException;
import com.saubhagya.projectservice.exceptions.ProductNotFoundException;
import com.saubhagya.projectservice.models.Category;
import com.saubhagya.projectservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {
    Product getProductById(String id) throws DBTImeoutException, DBNotFoundException, ProductNotFoundException;

    Page<Product> getAllProducts(Integer pageSize, Integer pageNumber, String sortField, String sortOrder);

    List<Category> getAllCategory();

    //For RealDB Extract
    Product createProduct(Product product);

    List<Product> getProductsByCategory(String category);

    // to create objects in FakeStore DB
    Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO);

    List<Product> searchProduct(String searchText);
}
