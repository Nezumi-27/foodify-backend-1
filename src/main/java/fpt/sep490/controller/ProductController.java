package fpt.sep490.controller;

import fpt.sep490.payload.ProductDto;
import fpt.sep490.payload.ProductResponse;
import fpt.sep490.payload.ProductResponsePageable;
import fpt.sep490.service.ProductService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("CRUD APIs for Products Resources")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("Create Product")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @ApiOperation("Get All Products")
    @GetMapping
    public ProductResponsePageable getAllProduct(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return productService.getAllProducts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Get Products By Shop Id")
    @GetMapping("/shops/{id}")
    public ProductResponsePageable getProductsByShopId(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @PathVariable(value = "id") Long shopId
    ){
        return productService.getAllProductsByShopId(shopId, pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Get Products By Categories")
    @GetMapping("/categories/{id}")
    public ProductResponsePageable getProductsByCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "id") List<Long> categoryIds
            ){
        return productService.getAllProductsByCategoryIds(categoryIds, pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Update Product By Id")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(value = "id") Long productId,
                                         @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.updateProduct(productId, productDto));
    }

    @ApiOperation("Delete product by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product Deleted Successfully!");
    }
}
