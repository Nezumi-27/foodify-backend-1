package fpt.sep490.service;

import fpt.sep490.payload.ProductImageDto;
import fpt.sep490.payload.ProductImageResponsePageable;

public interface ProductImageService {
    ProductImageDto createProductImage(Long productId, ProductImageDto productImageDto);

    ProductImageResponsePageable getAllProductImages(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductImageResponsePageable getProductImagesByProductId(Long productId, int pageNo, int pageSize, String sortBy, String sortDir);
    ProductImageDto getProductImageById(Long productId, Long productImageId);
    ProductImageDto updateProductById(Long productId, Long productImageId, ProductImageDto productImageDto);
    void deleteProductById(Long productId, Long productImageId);
}
