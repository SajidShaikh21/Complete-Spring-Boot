package com.bitsnbytes.product.controller;

import com.bitsnbytes.product.dto.ProductDTO;
import com.bitsnbytes.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

         @Autowired
         private ProductService productService;

         @PostMapping
         public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO)
         {
           ProductDTO createdProduct =productService.createProduct(productDTO);
           return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
         }

         @PutMapping("/{id}")
         public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id)
         {
            return productService.updateProduct(id,productDTO);
         }

         @GetMapping
          public List<ProductDTO> getAllProduct()
         {
             return productService.getAllProducts();
         }

         @GetMapping("/{id}")
         public ProductDTO getProductById(@PathVariable Long id)
         {
          return productService.getProductById(id);
         }

         @DeleteMapping("/{id}")
         public String deleteProduct(@PathVariable Long id)
         {
             return productService.deleteProduct(id);
         }
}
