//package com.saubhagya.projectservice;
//
//import com.saubhagya.projectservice.models.Product;
//import com.saubhagya.projectservice.projections.ProductProjections;
//import com.saubhagya.projectservice.repository.ProductRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//class ProjectServiceApplicationTests {
//
//    ProductRepository productRepository;
//
//    @Autowired
//    public ProjectServiceApplicationTests(ProductRepository productRepository){
//        this.productRepository = productRepository;
//    }
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void testAllProductUsingHQL(){
//        List<Product> products = productRepository.findAllProducts();
//        for(Product product : products){
//            System.out.println(product.getName());
//        }
//    }
//
//    @Test
//    void testProductsUsingCategoryHQL(){
//        List<Product> products = productRepository.findProductsByCategoryName("Laptop");
//        for(Product product : products){
//            System.out.println(product.getName());
//        }
//    }
//
//    @Test
//    void testProductsUsingCategorySQL(){
//        List<Product> products = productRepository.findProductsByCategoryNameNative("Phone");
//        for(Product product : products){
//            System.out.println(product.getName());
//        }
//    }
//
//    @Test
//    void sqlFetchUsingProjectionsNative(){
//        List<ProductProjections> products = productRepository.fetchUsingProjectionsNative("Laptop");
//        for(ProductProjections product : products){
//            System.out.println(product.getId() + " " + product.getName());
//        }
//    }
//
//    @Test
//    void sqlFetchUsingProjectionsHQL(){
//        List<ProductProjections> products = productRepository.fetchUsingProjectionsHQL("Laptop");
//        for(ProductProjections product : products){
//            System.out.println(product.getId() + " " + product.getName());
//        }
//    }
//
////    @Test
////    void testAllProductsUsingHQL(){
////        List<Product> productList = productRepository.sabKuchDedo();
////        for(Product product: productList){
////            System.out.println(product.getName());
////        }
////    }
////
////    @Test
////    void testAllProductsUsingHQLAndCatName(){
////        List<Product> productList = productRepository.allProductsGivenCatNameUsingHQL("laptop");
////        for(Product product: productList){
////            System.out.println(product.getName());
////        }
////    }
////
////    @Test
////    void sqlChalaLo(){
////        List<Product> productList = productRepository.properSQLQuery("laptop");
////        for(Product product: productList){
////            System.out.println(product.getName());
////        }
////    }
//
//}
