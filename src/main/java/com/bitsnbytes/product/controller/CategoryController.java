package com.bitsnbytes.product.controller;

import com.bitsnbytes.product.dto.CategoryDTO;
import com.bitsnbytes.product.dto.ProductDTO;
import com.bitsnbytes.product.exception.CategoryAlreadyException;
import com.bitsnbytes.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO)
    {

            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
      // return  new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories()
    {
        return categoryService.getAllCategories();
    }


    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id)
    {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id)
    {
      return categoryService.deleteCategory(id);
    }
}
