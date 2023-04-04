package fpt.sep490.service.impl;

import fpt.sep490.entity.*;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.*;
import fpt.sep490.repository.*;
import fpt.sep490.service.ProductService;
import fpt.sep490.utils.StringConverter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final ModelMapper mapper;
    private final StringConverter stringConverter;

    private final String newCategoryImage = "https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8";
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ShopRepository shopRepository, ModelMapper mapper, StringConverter stringConverter,
                              UserRepository userRepository,
                              OrderRepository orderRepository,
                              OrderDetailRepository orderDetailRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.mapper = mapper;
        this.stringConverter = stringConverter;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public ProductResponse createProduct(ProductDto productDto) {
        List<String> categoryNames = productDto.getCategoryNames();

        List<Category> categories = new ArrayList<>();

        for(String categoryName : categoryNames){
            categoryName = stringConverter.convertString(categoryName);

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

        Set<Category> categorySet = new HashSet<>(categories);

        Product product = new Product();
        product.setName(stringConverter.convertString(productDto.getName()));
        product.setDescription(productDto.getDescription());
        product.setIsEnabled(productDto.getIsEnabled());
        product.setCost(productDto.getCost());
        product.setDiscountPercent(productDto.getDiscountPercent());
        product.setAverageRating(0);
        product.setReviewCount(0);
        product.setShop(shop);
        product.setCategories(categorySet);

        Product newProduct = productRepository.save(product);

        return mapper.map(newProduct, ProductResponse.class);
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
    public ProductResponsePageable getAllEnableProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> products = productRepository.findProductsByIsEnabled(true, pageable);
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
    public RandomProductResponsePageable getRandomEnableProducts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        List<Product> products = productRepository.findProductsByIsEnabled(true);
        List<Product> shuffle = new ArrayList<>(products);
        Collections.shuffle(shuffle);

        int start = pageNo * pageSize;
        int end = Math.min(start + pageSize, products.size());

        List<Product> productList = shuffle.subList(start, end);
        Page<Product> page = new PageImpl<>(productList, pageable, products.size());
        Set<ProductResponse> content = productList.stream().map(product -> mapper.map(product, ProductResponse.class)).collect(Collectors.toSet());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(page.getNumber());
        pageableDto.setPageSize(page.getSize());
        pageableDto.setTotalElements(page.getTotalElements());
        pageableDto.setTotalPages(page.getTotalPages());
        pageableDto.setLast(page.isLast());

        RandomProductResponsePageable responses = new RandomProductResponsePageable();
        responses.setProducts(content);
        responses.setPage(pageableDto);
        return responses;
    }

    @Override
    public Set<ProductSimpleResponse> getAllEnableProductsNoPageable() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> mapper.map(product, ProductSimpleResponse.class)).collect(Collectors.toSet());
    }

    @Override
    public List<ProductSimpleResponse> getAllProductsByShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));

        List<Product> products = productRepository.findProductsByShop(shop);
        return products.stream().map(product -> mapper.map(product, ProductSimpleResponse.class)).collect(Collectors.toList());
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
    public ProductResponsePageable findProductsByCategoryIdsAndName(List<Long> categoryIds, String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<Category> categories = categoryRepository.findCategoriesByIdIn(categoryIds);
        Page<Product> products = productRepository.findDistinctByCategoriesInAndIsEnabledAndNameContainingOrNameLike(categories, true, name, name, pageable);
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
            categoryName = stringConverter.convertString(categoryName);

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

        Set<Category> categorySet = new HashSet<>(categories);

        product.setName(stringConverter.convertString(productDto.getName()));
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
    public ProductResponsePageable findEnableProductsByName(String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> products = productRepository.findProductsByIsEnabledAndNameContaining(true, name, pageable);
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

    @Override
    public StringBoolObject productHasBeenBoughtByUser(Long productId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        List<Order> orders = orderRepository.findOrdersByUser(user);

        for (Order order: orders){
            if (order.getStatus().equals("COMPLETED")){
                List<OrderDetail> details = orderDetailRepository.findOrderDetailsByOrder(order);
                for (OrderDetail detail : details){
                    if (detail.getProduct().getId().equals(productId)){
                        return new StringBoolObject("isBought", true);
                    }
                }
            }

        }

        return new StringBoolObject("isBought", false);
    }
}
