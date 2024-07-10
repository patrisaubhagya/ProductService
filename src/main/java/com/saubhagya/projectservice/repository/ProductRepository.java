package com.saubhagya.projectservice.repository;

import com.saubhagya.projectservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Product getProductById(Long id);

    List<Product> findAll();

    List<Product> getProductByCategoryName(String CategoryName);
}
