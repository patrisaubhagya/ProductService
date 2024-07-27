package com.saubhagya.projectservice.services;

import com.saubhagya.projectservice.dto.FakeStoreRequestDTO;
import com.saubhagya.projectservice.exceptions.DBNotFoundException;
import com.saubhagya.projectservice.exceptions.DBTImeoutException;
import com.saubhagya.projectservice.exceptions.ProductNotFoundException;
import com.saubhagya.projectservice.models.Category;
import com.saubhagya.projectservice.models.Product;
import com.saubhagya.projectservice.repository.CategoryRepository;
import com.saubhagya.projectservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("DatabaseProductService")
public class RealProductService implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;


    @Autowired
    public RealProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public Product getProductById(String id) throws DBTImeoutException, DBNotFoundException, ProductNotFoundException{
        Product product = productRepository.getProductById(Long.valueOf(id));
        return product;
    }

    @Override
    public Page<Product> getAllProducts(Integer pageSize, Integer pageNumber, String sortField, String sortOrder) {
        Sort.Direction direction = sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortField);
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


    @Override
    public Product createProduct(Product product) {
        Optional<Category> optionalCategory = categoryRepository.getCategoryByName(product.getCategory().getName());

        if(optionalCategory.isEmpty()){
            Category category = new Category();
            category.setName(product.getCategory().getName());
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        } else {
            Category alreadyPresentCategory = optionalCategory.get();
            product.setCategory(alreadyPresentCategory);
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {

        List<Product> response =  productRepository.getProductByCategoryName(categoryName);
        return response;

    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        return null;
    }

    @Override
    public List<Product> searchProduct(String searchText) {
    List <Product> productsFromDB = productRepository.getProductsByNameContaining(searchText);
    return productsFromDB;
    }


}
