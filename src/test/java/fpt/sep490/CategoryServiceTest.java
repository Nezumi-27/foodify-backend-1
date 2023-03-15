package fpt.sep490;

import fpt.sep490.entity.Category;
import fpt.sep490.payload.CategoryDto;
import fpt.sep490.repository.CategoryRepository;
import fpt.sep490.service.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private ModelMapper mapper;

    @Test
    public void stage1_testCreateCategory(){
        Category category = new Category(1L, "cat_test", "cat_test_img");
        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);

        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        CategoryDto newCategory = categoryService.createCategory(categoryDto);

        Assert.assertNotNull(newCategory);
        Assert.assertEquals("cat_test", categoryDto.getName());
    }

    @Test
    public void stage2_testGetAllCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "cat_test1", "cat_test1_img"));
        categories.add(new Category(2L, "cat_test2", "cat_test2_img"));
        categories.add(new Category(3L, "cat_test3", "cat_test3_img"));

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDto> categoryDtos = categoryService.getAllCategories();

        Assert.assertNotNull(categoryDtos);
    }

    @Test
    public void stage3_testGetRoleById(){
        Category category = new Category(1L, "cat_test", "cat_test_img");

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        CategoryDto getCategory = categoryService.getCategory(1L);

        Assert.assertNotNull(getCategory);
        Assert.assertEquals("cat_test", getCategory.getName());
    }

    @Test
    public void stage4_testUpdateRoleById(){
        Category category = new Category(1L, "cat_test", "cat_test_img");
        Category categoryU = new Category(1L, "cat_testU", "cat_testU_img");
        CategoryDto categoryUDto = mapper.map(categoryU, CategoryDto.class);

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(categoryU);

        CategoryDto updateDto = categoryService.updateCategory(categoryUDto, 1L);

        Assert.assertNotNull(updateDto);
        Assert.assertEquals("cat_testU", updateDto.getName());

        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    public void stage5_testDeleteRoleById(){
        Category category = new Category(1L, "cat_test", "cat_test_img");

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        categoryService.deleteCategory(1L);

        Mockito.verify(categoryRepository, Mockito.times(1)).delete(category);
    }
}
