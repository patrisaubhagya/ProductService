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
import org.springframework.data.domain.Page;
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
    @Qualifier("FakeStoreProductService")
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
    public ResponseEntity<ListProductsResponseDTO> getAllProducts(
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
            ){
        Page<Product> products = productService.getAllProducts(pageSize, pageNumber, sortField, sortOrder);
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
    public Product createProduct(@RequestBody CreateProductDTO createProductDTO) {
        Product toBeSavedProduct = createProductDTO.toProduct();
        Product savedProduct = productService.createProduct(toBeSavedProduct);
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
