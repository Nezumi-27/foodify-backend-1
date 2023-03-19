package fpt.sep490.service.impl;

import fpt.sep490.entity.Category;
import fpt.sep490.entity.Product;
import fpt.sep490.entity.Shop;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.PageableDto;
import fpt.sep490.payload.ProductDto;
import fpt.sep490.payload.ProductResponse;
import fpt.sep490.payload.ProductResponsePageable;
import fpt.sep490.repository.CategoryRepository;
import fpt.sep490.repository.ProductImageRepository;
import fpt.sep490.repository.ProductRepository;
import fpt.sep490.repository.ShopRepository;
import fpt.sep490.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ShopRepository shopRepository;
    private ProductImageRepository productImageRepository;
    private ModelMapper mapper;

    private String newCategoryImage = "https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8";

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ShopRepository shopRepository, ProductImageRepository productImageRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.productImageRepository = productImageRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductResponse createProduct(ProductDto productDto) {
        List<String> categoryNames = productDto.getCategoryNames();

        List<Category> categories = new ArrayList<>();

        for(String categoryName : categoryNames){
            categoryName = categoryName.replaceAll("\\s+", " ").trim().toLowerCase();
            categoryName = categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1);

            if(categoryRepository.existsByName(categoryName)){
                Category category = categoryRepository.findByName(categoryName)
                        .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Category not found"));
                categories.add(category);
            }
            else {
                Category category = new Category();
                category.setName(categoryName);
                category.setImageUrl(newCategoryImage);
                Category newCategory = categoryRepository.save(category);
                categories.add(newCategory);
            }
        }

        Shop shop = shopRepository.findById(productDto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", productDto.getShopId()));

        Set<Category> categorySet = new HashSet<Category>(categories);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setIsEnabled(productDto.getIsEnabled());
        product.setCost(productDto.getCost());
        product.setDiscountPercent(productDto.getDiscountPercent());
        product.setAverageRating(0);
        product.setReviewCount(0);
        product.setShop(shop);
        product.setCategories(categorySet);

        Product newProduct = productRepository.save(product);

        ProductResponse productResponse = mapper.map(newProduct, ProductResponse.class);
        return productResponse;
    }

    @Override
    public ProductResponsePageable getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> products = productRepository.findAll(pageable);
        List<Product> productList = products.getContent();
        List<ProductResponse> content = productList.stream().map(product -> mapper.map(product, ProductResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(products.getNumber());
        pageableDto.setPageSize(products.getSize());
        pageableDto.setTotalElements(products.getTotalElements());
        pageableDto.setTotalPages(products.getTotalPages());
        pageableDto.setLast(products.isLast());

        ProductResponsePageable responses = new ProductResponsePageable();
        responses.setProducts(content);
        responses.setPage(pageableDto);
        return responses;
    }

    @Override
    public ProductResponsePageable getAllProductsByShopId(Long shopId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(()-> new ResourceNotFoundException("Shop", "id", shopId));

        Page<Product> products = productRepository.findProductsByShopId(shopId, pageable);
        List<Product> productList = products.getContent();
        List<ProductResponse> content = productList.stream().map(product -> mapper.map(product, ProductResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(products.getNumber());
        pageableDto.setPageSize(products.getSize());
        pageableDto.setTotalElements(products.getTotalElements());
        pageableDto.setTotalPages(products.getTotalPages());
        pageableDto.setLast(products.isLast());

        ProductResponsePageable responses = new ProductResponsePageable();
        responses.setProducts(content);
        responses.setPage(pageableDto);
        return responses;
    }

    @Override
    public ProductResponsePageable getAllProductsByCategoryIds(List<Long> categoryIds, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<Category> categories = categoryRepository.findCategoriesByIdIn(categoryIds);
        Page<Product> products = productRepository.findDistinctByCategoriesIn(categories, pageable);
        List<Product> productList = products.getContent();
        List<ProductResponse> content = productList.stream().map(product -> mapper.map(product, ProductResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(products.getNumber());
        pageableDto.setPageSize(products.getSize());
        pageableDto.setTotalElements(products.getTotalElements());
        pageableDto.setTotalPages(products.getTotalPages());
        pageableDto.setLast(products.isLast());

        ProductResponsePageable responses = new ProductResponsePageable();
        responses.setProducts(content);
        responses.setPage(pageableDto);
        return responses;
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Set<Category> productCategories = product.getCategories();
        for(Category cat : productCategories){
            cat.getProducts().remove(product);
            categoryRepository.save(cat);
        }

        List<String> categoryNames = productDto.getCategoryNames();

        List<Category> categories = new ArrayList<>();

        for(String categoryName : categoryNames){
            categoryName = categoryName.replaceAll("\\s+", " ").trim().toLowerCase();
            categoryName = categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1);

            if(categoryRepository.existsByName(categoryName)){
                Category category = categoryRepository.findByName(categoryName)
                        .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Category not found"));
                categories.add(category);
            }
            else {
                Category category = new Category();
                category.setName(categoryName);
                category.setImageUrl(newCategoryImage);
                Category newCategory = categoryRepository.save(category);
                categories.add(newCategory);
            }
        }

        Set<Category> categorySet = new HashSet<Category>(categories);

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setIsEnabled(productDto.getIsEnabled());
        product.setCost(productDto.getCost());
        product.setDiscountPercent(productDto.getDiscountPercent());
        product.setAverageRating(productDto.getAverageRating());
        product.setReviewCount(productDto.getReviewCount());
        product.setCategories(new HashSet<>());

        Product updateProduct = productRepository.save(product);
        updateProduct.setCategories(categorySet);
        updateProduct = productRepository.save(updateProduct);
        return mapper.map(updateProduct, ProductResponse.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);
    }

    @Override
    public ProductResponsePageable findProductsByName(String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> products = productRepository.findProductsByNameLikeOrNameContaining(name, name, pageable);
        List<Product> list = products.getContent();
        List<ProductResponse> content = list.stream().map(product -> mapper.map(product, ProductResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(products.getNumber());
        pageableDto.setPageSize(products.getSize());
        pageableDto.setTotalElements(products.getTotalElements());
        pageableDto.setTotalPages(products.getTotalPages());
        pageableDto.setLast(products.isLast());

        ProductResponsePageable responses = new ProductResponsePageable();
        responses.setProducts(content);
        responses.setPage(pageableDto);
        return responses;
    }
}
