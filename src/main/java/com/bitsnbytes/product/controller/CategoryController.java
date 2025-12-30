package com.bitsnbytes.product.controller;

import com.bitsnbytes.product.dto.CategoryDTO;
import com.bitsnbytes.product.dto.ProductDTO;
import com.bitsnbytes.product.exception.CategoryAlreadyException;
import com.bitsnbytes.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "Category REST API CRUD Operation",
        description = "CREATE READ UPDATE  DELETE Operation for Category Operation REST API"
)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(
            summary = "Create Category",
            description = "REST API Create Category"
    )
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO)
    {

            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
      // return  new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Fetch All Category",
            description = "REST API to fetch all category"
    )
    @GetMapping
    public List<CategoryDTO> getAllCategories()
    {
        return categoryService.getAllCategories();
    }


    @Operation(
            summary = "Fetch Category By ID",
            description = "REST API to fetch category By ID"
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id)
    {
        return categoryService.getCategoryById(id);
    }


    @Operation(
            summary = "Delete Category",
            description = "Delete Category By ID"
    )
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id)
    {
      return categoryService.deleteCategory(id);
    }
}
