package fpt.sep490.controller;

import fpt.sep490.payload.ProductImageDto;
import fpt.sep490.payload.ProductImageResponsePageable;
import fpt.sep490.service.ProductImageService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("CRUD APIs for Product Image Resources")
@RestController
@RequestMapping
public class ProductImageController {
    private ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @ApiOperation("Create Product Image")
    @PostMapping("/products/{productId}/images")
    public ResponseEntity<ProductImageDto> createImage(@PathVariable(value = "productId") Long productId,
                                                       @RequestBody ProductImageDto productImageDto){
        return new ResponseEntity<>(productImageService.createProductImage(productId, productImageDto), HttpStatus.CREATED);
    }

    @ApiOperation("Get All Product Images")
    @GetMapping("/products/images")
    public ProductImageResponsePageable getAllImages(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return productImageService.getAllProductImages(pageNo, pageSize, sortBy, sortDir);
    }
}
