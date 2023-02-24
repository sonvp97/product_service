package com.cg.service.product;


import com.cg.dto.CategoryDto;
import com.cg.dto.ProductDto;
import com.cg.entity.Category;
import com.cg.entity.Product;
import com.cg.repo.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Iterable<ProductDto> findAll() {
        Iterable<Product> entities = productRepository.findAll();
        return StreamSupport.stream(entities.spliterator(),true)
                .map(entity -> {
                    ProductDto productDto = modelMapper.map(entity, ProductDto.class);
                    productDto.setCategory_name(entity.getCategory().getName().toString());
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        Product product = productRepository.findById(id).get();
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return Optional.ofNullable(productDto);
    }

    @Override
    public void save(ProductDto productDto) {
        Product product = modelMapper.map(productDto,Product.class);
        productRepository.save(product);
        modelMapper.map (product, Product.class);
    }


    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);

    }
}
