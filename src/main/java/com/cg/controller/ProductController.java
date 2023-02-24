package com.cg.controller;

import com.cg.dto.CategoryDto;
import com.cg.dto.ProductDto;
import com.cg.payload.ResponseMessage;
import com.cg.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        Iterable<ProductDto> productDtos = productService.findAll();
        if (productDtos == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        Optional<ProductDto> productDto = productService.findById(id);
        if (productDto == null) {
            return new ResponseEntity<ProductDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {

        if (productDto.getName().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("The name is required!"), HttpStatus.OK);
        }
        productService.save(productDto);
        return new ResponseEntity<>(new ResponseMessage("Create product success!"), HttpStatus.OK);
    }



    @DeleteMapping ("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
        Optional<ProductDto> userDtoOptional = productService.findById(id);
        if (!userDtoOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(userDtoOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<ProductDto> updateProduct( @PathVariable(value = "id")Long id, @RequestBody ProductDto productDto) {
        Optional<ProductDto> productDtoOptional = productService.findById(id);
        if (!productDtoOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productDto.setId(productDtoOptional.get().getId());
        productService.save(productDto);
        return new ResponseEntity<>( productDto, HttpStatus.OK);
    }
}

