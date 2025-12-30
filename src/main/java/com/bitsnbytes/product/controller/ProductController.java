package com.bitsnbytes.product.controller;

import com.bitsnbytes.product.dto.ProductDTO;
import com.bitsnbytes.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Product REST API CRUD Operation",
        description = "CREATE READ UPDATE  DELETE Operation for Product Operation REST API"
)
@RestController
@RequestMapping("/api/products")
public class ProductController {

         @Autowired
         private ProductService productService;


         @Operation(
            summary = "Create Products",
            description = "Create product"
         )
         @PostMapping
         public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO)
         {
           ProductDTO createdProduct =productService.createProduct(productDTO);
           return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
         }

           @Operation(
            summary = "Update Products",
            description = "REST API to Update product"
           )
         @PutMapping("/{id}")
         public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id)
         {
            return productService.updateProduct(id,productDTO);
         }


         @Operation(
                 summary = "Fetch All Products",
                 description = "REST API to fetch all product"
         )
         @GetMapping
          public List<ProductDTO> getAllProduct()
         {
             return productService.getAllProducts();
         }


          @Operation(
            summary = "Fetch Products By ID",
            description = "REST API to fetch product ID"
          )
         @GetMapping("/{id}")
         public ProductDTO getProductById(@PathVariable Long id)
         {
          return productService.getProductById(id);
         }


         @Operation(
            summary = "Delete Products",
            description = "Delete product By ID"
         )
         @DeleteMapping("/{id}")
         public String deleteProduct(@PathVariable Long id)
         {
             return productService.deleteProduct(id);
         }
}
