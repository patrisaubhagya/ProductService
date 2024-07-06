package com.saubhagya.projectservice.services;

import com.saubhagya.projectservice.dto.FakeStoreRequestDTO;
import com.saubhagya.projectservice.dto.FakeStoreResponseDTO;
import com.saubhagya.projectservice.exceptions.ProductNotFoundException;
import com.saubhagya.projectservice.models.Category;
import com.saubhagya.projectservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Product getProductById(String id) throws ProductNotFoundException {
        FakeStoreResponseDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreResponseDTO.class);

        if(response == null){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponseDTO[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreResponseDTO[].class);

        List<Product> productList = new ArrayList<Product>();
        for (FakeStoreResponseDTO response : responseArray) {
            Product product = response.toProduct();
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        FakeStoreResponseDTO savedProductResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreRequestDTO, FakeStoreResponseDTO.class);

        return savedProductResponse.toProduct();
    }
}
