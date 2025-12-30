package com.bitsnbytes.product.service;

import com.bitsnbytes.product.dto.CategoryDTO;
import com.bitsnbytes.product.entity.Category;
import com.bitsnbytes.product.exception.CategoryAlreadyException;
import com.bitsnbytes.product.mapper.CategoryMapper;
import com.bitsnbytes.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO)
    {
        Optional<Category>optionalCategory= categoryRepository.findByName(categoryDTO.getName());
        if (optionalCategory.isPresent()){
            throw new CategoryAlreadyException(
                    "Category '" + categoryDTO.getName() + "' already exists"
            );

        }

        Category entity = CategoryMapper.toCategoryEntity(categoryDTO);
        Category save = categoryRepository.save(entity);
        return CategoryMapper.toCategoryDTO(save);
    }

    public List<CategoryDTO>getAllCategories()
    {
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }

    public CategoryDTO getCategoryById(Long id)
    {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("category not found"));
         return CategoryMapper.toCategoryDTO(category);
    }

    public String deleteCategory(Long id)
    {
        categoryRepository.deleteById(id);
        return "Category with id " + id + " has been deleted!";
    }

}
