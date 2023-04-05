package fpt.sep490.service;

import fpt.sep490.payload.AddressResponsePageable;
import fpt.sep490.payload.CategoryDto;
import fpt.sep490.payload.CategoryResponsePageable;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryResponsePageable getRandomCategories(int pageNo, int pageSize);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    void deleteCategory(Long categoryId);
}
