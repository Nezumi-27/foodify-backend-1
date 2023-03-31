package fpt.sep490.service;

import fpt.sep490.payload.*;

import java.util.List;
import java.util.Set;

public interface ProductService {
    ProductResponse createProduct(ProductDto productDto);

    ProductResponsePageable getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductResponsePageable getAllEnableProducts(int pageNo, int pageSize, String sortBy, String sortDir);

    RandomProductResponsePageable getRandomEnableProducts(int pageNo, int pageSize);

    Set<ProductSimpleResponse> getAllEnableProductsNoPageable();

    ProductResponsePageable getAllProductsByShopId(Long shopId, int pageNo, int pageSize, String sortBy, String sortDir);

    ProductResponsePageable getAllProductsByCategoryIds(List<Long> categoryIds, int pageNo, int pageSize, String sortBy, String sortDir);

    ProductResponse getProductById(Long productId);

    ProductResponse updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);

    ProductResponsePageable findProductsByName(String name, int pageNo, int pageSize, String sortBy, String sortDir);
}
