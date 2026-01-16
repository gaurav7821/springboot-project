package com.gogos.spring_project.service.impl;

import com.gogos.spring_project.entities.Category;
import com.gogos.spring_project.exceptions.ResourceNotFoundException;
import com.gogos.spring_project.payloads.CategoryDto;
import com.gogos.spring_project.repositories.CategoryRepo;
import com.gogos.spring_project.service.CategoryService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper){
        this.categoryRepo=categoryRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category cat = modelMapper.map(categoryDto, Category.class);
        Category addedCat = categoryRepo.save(cat);

        return modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Category updatedCat = categoryRepo.save(cat);

        return modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {

        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> catDtos = categories.stream().map((cat)-> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

        return catDtos;
    }
}
