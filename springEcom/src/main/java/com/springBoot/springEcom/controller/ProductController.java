package com.springBoot.springEcom.controller;
import com.springBoot.springEcom.model.Product;
import com.springBoot.springEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int postId){
        return new ResponseEntity<>(productService.getProductById(postId),HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable("productId") int productId){
        Product imageProduct = productService.getProductById(productId);
        if(imageProduct.getId()>0 && imageProduct.getImageData()!=null){
            return new ResponseEntity<>(imageProduct.getImageData(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProductsByKeyword(@RequestParam String keyword){
        System.out.println("searching with this keyword: "+keyword);
        return new ResponseEntity<>(productService.getProductsByKeyword(keyword),HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            Product addedProduct = productService.addorUpdateProduct(product,imageFile);
            return new ResponseEntity<>(addedProduct,HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") int id,@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            Product updateedProduct = productService.addorUpdateProduct(product,imageFile);
            return new ResponseEntity<>(updateedProduct,HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
        Product product = productService.getProductById(id);
        if(product.getId()>0){
            productService.deleteProduct(id);
            return new ResponseEntity<>("Successfully deleted",HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>("Product was not present",HttpStatus.NOT_FOUND);
        }
    }
}
