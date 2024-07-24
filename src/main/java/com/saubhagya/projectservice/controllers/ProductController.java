package com.saubhagya.projectservice.controllers;

import com.saubhagya.projectservice.dto.*;
import com.saubhagya.projectservice.exceptions.DBNotFoundException;
import com.saubhagya.projectservice.exceptions.DBTImeoutException;
import com.saubhagya.projectservice.exceptions.ProductNotFoundException;
import com.saubhagya.projectservice.models.Category;
import com.saubhagya.projectservice.models.Product;
import com.saubhagya.projectservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//scalerRDSUser
//scalerRDSPass
//3306

@RestController
public class ProductController {

    @Autowired
    @Qualifier("DatabaseProductService")
    ProductService productService;

    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") String id) throws DBTImeoutException, DBNotFoundException, ProductNotFoundException {
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

    @GetMapping("/categories")
    public ResponseEntity<ListCategoryResponseDTO> getAllCategory() {
       List<Category> categories = productService.getAllCategory();

       ListCategoryResponseDTO responseDTO = new ListCategoryResponseDTO();

       responseDTO.setCategoryList(categories);
       responseDTO.setResponseMessage("Success");
       ResponseEntity<ListCategoryResponseDTO> responseEntity= new ResponseEntity<>(responseDTO, HttpStatus.OK);
       return responseEntity;
    }

    @GetMapping("/categorysearch")
    public List<Product> getProductsByCategoryName(@RequestParam("category") String categoryName) {
        System.out.println("Search Text : " + categoryName);
        List<Product> products = productService.getProductsByCategory(categoryName);
        return products;
    }

    @GetMapping("/productSearch")
    public List<Product> getProductsByTextName(@RequestParam("text")  String searchText) {
        System.out.println("Search Text : " + searchText);
        List<Product> products = productService.searchProduct(searchText);
        return products;
    }


    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
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
