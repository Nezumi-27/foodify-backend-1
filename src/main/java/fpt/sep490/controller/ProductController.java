package fpt.sep490.controller;

import fpt.sep490.payload.*;
import fpt.sep490.service.ProductService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Api("CRUD APIs for Products Resources")
@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Create Product")
    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductDto productDto){
        System.out.println(productDto.toString());
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @ApiOperation("Get All Products")
    @GetMapping("/products")
    public ProductResponsePageable getAllProduct(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return productService.getAllProducts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Get All Enable Products")
    @GetMapping("/products/enable")
    public ProductResponsePageable getAllProductEnable(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return productService.getAllEnableProducts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Get Random Enable Products")
    @GetMapping("/products/random")
    public ResponseEntity<RandomProductResponsePageable> getRandomProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize
    ){
        return ResponseEntity.ok(this.productService.getRandomEnableProducts(pageNo, pageSize));
    }

    @ApiOperation("Get All Enable Products No Pageable - User Page")
    @GetMapping("/v1/products/enable")
    public ResponseEntity<Set<ProductSimpleResponse>> getAllProductEnableNoPageable(){
        return ResponseEntity.ok(this.productService.getAllEnableProductsNoPageable());
    }

    @ApiOperation("Get All Products No Pageable - Admin")
    @GetMapping("/v1/products")
    public ResponseEntity<List<ProductSimpleResponse>> getAllProductNoPageable(){
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    @ApiOperation("Get Products By Shop Id")
    @GetMapping("/products/shops/{id}")
    public ProductResponsePageable getProductsByShopId(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @PathVariable(value = "id") Long shopId
    ){
        return productService.getAllProductsByShopId(shopId, pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Get All Products By Shop Id No Pageable")
    @GetMapping("/products/shops")
    public ResponseEntity<List<ProductSimpleResponse>> getProductsByShopIdNoPageable(
            @RequestParam(value = "id") Long shopId
    ){
        return ResponseEntity.ok(productService.getAllProductsByShop(shopId));
    }

    @ApiOperation("Get Products By Categories")
    @GetMapping("/products/categories")
    public ProductResponsePageable getProductsByCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "id") List<Long> categoryIds
            ){
        return productService.getAllProductsByCategoryIds(categoryIds, pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Search Enable Products By Categories And Name")
    @GetMapping("/products/categoriesAndName")
    public ResponseEntity<ProductResponsePageable> getProductsByCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "id") List<Long> categoryIds,
            @RequestParam(value = "name") String name
    ){
        return ResponseEntity.ok(productService.findProductsByCategoryIdsAndName(categoryIds, name, pageNo, pageSize, sortBy, sortDir));
    }

    @ApiOperation("Get Product By Id")
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(value = "id") Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Update Product By Id")
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(value = "id") Long productId,
                                         @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.updateProduct(productId, productDto));
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Delete product by Id")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product Deleted Successfully!");
    }

    @ApiOperation("Search enable products by name")
    @GetMapping("/products/search/enable")
    public ProductResponsePageable searchEnableByName(
            @RequestParam(value = "productName") String name,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return productService.findEnableProductsByName(name, pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Search products by name")
    @GetMapping("/products/search")
    public ProductResponsePageable searchByName(
            @RequestParam(value = "productName") String name,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return productService.findProductsByName(name, pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Search shop products by name")
    @GetMapping("/products/shop/{shopId}/search")
    public ProductResponsePageable searchShopProductsByName(
            @PathVariable(value = "shopId") Long shopId,
            @RequestParam(value = "productName") String name,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return productService.findShopProductByName(shopId, name, pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Check user has buy product")
    @GetMapping("/products/{productId}/user")
    public ResponseEntity<StringBoolObject> checkBought(@PathVariable(value = "productId") Long productId,
                                                @RequestParam(value = "userId") Long userId){
        return ResponseEntity.ok(productService.productHasBeenBoughtByUser(productId, userId));
    }
}
