package com.tnhandev.backend.controllers;

import com.tnhandev.backend.models.Product;
import com.tnhandev.backend.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/kw")
    public List<Product> getProductsByKeyword(
            @RequestParam String keyword,
            @RequestParam int numberOfPagesToCrawl) {
        return productService.getProductsByKeyword(keyword, numberOfPagesToCrawl);
    }

    @GetMapping("/ct")
    public List<Product> getProductsByCategory(@RequestParam String categoryUrl,
                                               @RequestParam int numberOfPagesToCrawl) {
        return productService.getProductsByCategory(categoryUrl, numberOfPagesToCrawl);
    }
}
