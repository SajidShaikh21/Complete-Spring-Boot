package com.bitsnbytes.product.service;


import com.bitsnbytes.product.dto.ProductDTO;
import com.bitsnbytes.product.entity.Category;
import com.bitsnbytes.product.entity.Product;
import com.bitsnbytes.product.exception.CategoryNotFoundException;
import com.bitsnbytes.product.mapper.ProductMapper;
import com.bitsnbytes.product.repository.CategoryRepository;
import com.bitsnbytes.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    //
    public ProductDTO createProduct(ProductDTO productDTO)
    {
        Category category = categoryRepository.
                findById(productDTO.getCategoryId()).orElseThrow(() ->
                        new CategoryNotFoundException(" category id " + productDTO.getCategoryId()+ " not found "));

        //dto to entity
        Product product = ProductMapper.toProductEntity(productDTO, category);
        Product product1 = productRepository.save(product);

         //entity to dto
        return ProductMapper.toProductDTO(product1);

    }

    //
    public List<ProductDTO> getAllProducts()
    {
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    //
    public ProductDTO getProductById(Long id)
    {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
       return ProductMapper.toProductDTO(product);
    }

    //
    public ProductDTO updateProduct(Long id, ProductDTO productDTO)
    {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    //
    public String deleteProduct(Long id)
    {
        productRepository.deleteById(id);
        return "Product " + id + "has been deleted";
    }




}
