package com.cg.service.category;


import com.cg.dto.CategoryDto;
import com.cg.entity.Category;
import com.cg.repo.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.parallelStream()
                .map(product -> modelMapper.map(product,CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        Category category = categoryRepository.findById(id).get();
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return Optional.ofNullable(categoryDto);
    }



    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
        modelMapper.map (category,Category.class);
    }


    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);

    }
}
