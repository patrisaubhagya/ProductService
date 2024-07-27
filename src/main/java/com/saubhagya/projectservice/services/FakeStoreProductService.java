package com.saubhagya.projectservice.services;

import com.saubhagya.projectservice.dto.FakeStoreRequestDTO;
import com.saubhagya.projectservice.dto.FakeStoreResponseDTO;
import com.saubhagya.projectservice.exceptions.DBNotFoundException;
import com.saubhagya.projectservice.exceptions.DBTImeoutException;
import com.saubhagya.projectservice.exceptions.ProductNotFoundException;
import com.saubhagya.projectservice.models.Category;
import com.saubhagya.projectservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("FakeStoreProductService")
//@Primary
public class FakeStoreProductService implements ProductService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Product getProductById(String productId) throws DBTImeoutException, DBNotFoundException, ProductNotFoundException {
        Product cachedProduct = (Product) redisTemplate.opsForHash().get("Product", "PRODUCT_" + productId);
        if (cachedProduct != null) {
            return cachedProduct;
        }

        FakeStoreResponseDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreResponseDTO.class
        );
        if(response == null){
            throw new ProductNotFoundException("product with id " + productId + " not found");
        }

        Product product = response.toProduct();
        redisTemplate.opsForHash().put("Product", "PRODUCT_" + productId, product);
        return product;
    }

    @Override
    public Page<Product> getAllProducts(Integer pageSize, Integer pageNumber, String sortField, String sortOrder) {
        List<Product> cachedProducts = (List<Product>) redisTemplate.opsForHash().get("Product", "PRODUCT_ALL");
        if (cachedProducts != null && cachedProducts.size() > 0){
            Page<Product> page = new PageImpl<>(cachedProducts);
            return page;
        }

        FakeStoreResponseDTO[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreResponseDTO[].class);

        List<Product> productList = new ArrayList<Product>();
        for (FakeStoreResponseDTO response : responseArray) {
            Product product = response.toProduct();
            productList.add(product);
        }

        redisTemplate.opsForHash().put("Product", "PRODUCT_ALL", productList);
        Page<Product> page = new PageImpl<Product>(productList);
        return page;
    }

    @Override
    public List<Category> getAllCategory() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        FakeStoreResponseDTO savedProductResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreRequestDTO, FakeStoreResponseDTO.class);

        return savedProductResponse.toProduct();
    }

    @Override
    public List<Product> searchProduct(String searchText) {
        return List.of();
    }


}
