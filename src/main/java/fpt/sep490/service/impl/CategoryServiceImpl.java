package fpt.sep490.service.impl;

import fpt.sep490.entity.Category;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.AddressResponsePageable;
import fpt.sep490.payload.CategoryDto;
import fpt.sep490.payload.CategoryResponsePageable;
import fpt.sep490.payload.PageableDto;
import fpt.sep490.repository.CategoryRepository;
import fpt.sep490.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);

        categoryRepository.save(category);

        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map((category -> mapper.map(category, CategoryDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponsePageable getRandomCategories(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Random random = new Random();

        Page<Category> categories = categoryRepository.findAll(pageable);

        List<Category> list = categories.getContent();
        List<Category> shuffleList = new ArrayList<>(list);
        Collections.shuffle(shuffleList);
        Set<CategoryDto> content = shuffleList.stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toSet());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(categories.getNumber());
        pageableDto.setPageSize(categories.getSize());
        pageableDto.setTotalElements(categories.getTotalElements());
        pageableDto.setTotalPages(categories.getTotalPages());
        pageableDto.setLast(categories.isLast());

        CategoryResponsePageable responses = new CategoryResponsePageable();
        responses.setCategories(content);
        responses.setPage(pageableDto);
        return responses;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setName(categoryDto.getName());
        category.setImageUrl(categoryDto.getImageUrl());

        Category updatedCategory = categoryRepository.save(category);

        return mapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.delete(category);
    }
}
