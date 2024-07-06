package com.saubhagya.projectservice.controllers;

import com.saubhagya.projectservice.dto.ErrorResponseDTO;
import com.saubhagya.projectservice.dto.FakeStoreRequestDTO;
import com.saubhagya.projectservice.dto.ListProductsResponseDTO;
import com.saubhagya.projectservice.dto.ProductResponseDTO;
import com.saubhagya.projectservice.exceptions.ProductNotFoundException;
import com.saubhagya.projectservice.models.Product;
import com.saubhagya.projectservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("FakeStoreProductService")
    ProductService productService;

    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") String id) throws ProductNotFoundException {
        System.out.println("Product Id : " + id);

        Product product = productService.getProductById(id);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProduct(product);
        ResponseEntity<ProductResponseDTO> responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        return responseEntity;

//        try{
//            Product product = productService.getProductById(id);
//            ProductResponseDTO productResponseDTO  = new ProductResponseDTO();
//            productResponseDTO.setProduct(product);
//            productResponseDTO.setResponseMessage("Success");
//
//            ResponseEntity<ProductResponseDTO> responseEntity= new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
//            return responseEntity;
//        }
//        catch(ProductNotFoundException e){
//             ProductResponseDTO productResponseDTO  = new ProductResponseDTO();
//             productResponseDTO.setProduct(null);
//             productResponseDTO.setResponseMessage(e.getMessage());
//
//             ResponseEntity<ProductResponseDTO> responseEntity= new ResponseEntity<>(productResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//             return responseEntity;
//        }

    }

    @GetMapping("/products")
    public ResponseEntity<ListProductsResponseDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ListProductsResponseDTO responseDTO = new ListProductsResponseDTO();

        responseDTO.setProductList(products);
        responseDTO.setResponseMessage("Success");

        ResponseEntity<ListProductsResponseDTO> responseEntity= new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody() FakeStoreRequestDTO fakeStoreRequestDTO) {
        Product savedProduct = productService.createProduct(fakeStoreRequestDTO);
        return savedProduct;
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public  ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException errorObject) {
//        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//        errorResponseDTO.setErrorMessage("From Controller " + errorObject.getMessage());
//        ResponseEntity<ErrorResponseDTO> responseEntity= new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        return responseEntity;
//    }

}
