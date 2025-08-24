package com.dspandiyan.bookstore.catalogservice.web.controllers;

import com.dspandiyan.bookstore.catalogservice.domain.PagedResult;
import com.dspandiyan.bookstore.catalogservice.domain.Product;
import com.dspandiyan.bookstore.catalogservice.domain.ProductNotFoundException;
import com.dspandiyan.bookstore.catalogservice.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping(value = "/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
