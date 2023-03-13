package fpt.sep490;

import fpt.sep490.entity.Category;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.payload.CategoryDto;
import fpt.sep490.repository.CategoryRepository;
import fpt.sep490.service.CategoryService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    public CategoryServiceTest() {
    }

    @Test
    public void stage1_testCreateCategory(){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Category_Test");
        categoryDto.setImageUrl("assets/test/category.png");

        CategoryDto newCategory = categoryService.createCategory(categoryDto);

        Assert.assertNotNull(newCategory);
        Assert.assertEquals("Category_Test", newCategory.getName());
    }

    @Test
    public void stage2_testGetAllCategories(){
        List<CategoryDto> categories = categoryService.getAllCategories();
        Assert.assertNotNull(categories);
        Assert.assertFalse(categories.isEmpty());
    }

    @Test
    public void stage3_testGetCategoryById(){
        Category category = categoryRepository.findByName("Category_Test")
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Category not found"));

        CategoryDto categoryGetDto = categoryService.getCategory(category.getId());
        Assert.assertNotNull(categoryGetDto);
        Assert.assertEquals("Category_Test", categoryGetDto.getName());
    }

    @Test
    public void stage4_testUpdateCategory(){
        Category category = categoryRepository.findByName("Category_Test")
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Category not found"));
        category.setName("Category_Test_Updated");

        CategoryDto categoryUpdated = categoryService.updateCategory(mapper.map(category, CategoryDto.class) ,category.getId());
        Assert.assertNotNull(categoryUpdated);
        Assert.assertEquals("Category_Test_Updated", categoryUpdated.getName());
    }

    @Test
    public void stage5_testDeleteCategory(){
        Category category = categoryRepository.findByName("Category_Test_Updated")
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Category not found"));
        categoryService.deleteCategory(category.getId());

        Optional<Category> deletedCategory = categoryRepository.findById(category.getId());
        Assert.assertTrue(deletedCategory.isEmpty());
    }
}
