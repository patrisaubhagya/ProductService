package com.saubhagya.projectservice.repository;

import com.saubhagya.projectservice.models.Product;
import com.saubhagya.projectservice.projections.ProductProjections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Product getProductById(Long id);

    Page<Product> findAll(Pageable pageable);

    List<Product> getProductByCategoryName(String CategoryName);

    List<Product> getProductsByNameContaining(String text);

    @Query("select p from Product p")
    List<Product> findAllProducts();

    @Query("select p from Product p where p.category.name = :categoryName")
    List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "select p.* from product p JOIN category c ON p.category_id = c.id WHERE c.name = :categoryName", nativeQuery = true)
    List<Product> findProductsByCategoryNameNative(@Param("categoryName") String categoryName);

    //HQL, With Alias for Projections

    @Query(value = "select p.id, p.name from product p LEFT JOIN category c ON p.category_id = c.id where c.name = :categoryName", nativeQuery = true)
    List<ProductProjections> fetchUsingProjectionsNative(@Param("categoryName") String categoryName);

    @Query(value = "select p from Product p where p.category.name = :categoryName", nativeQuery = false)
    List<ProductProjections> fetchUsingProjectionsHQL(@Param("categoryName") String categoryName);
}
