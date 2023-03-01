package fpt.sep490.service.impl;

import fpt.sep490.entity.Product;
import fpt.sep490.entity.ProductImage;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.PageableDto;
import fpt.sep490.payload.ProductImageDto;
import fpt.sep490.payload.ProductImageResponsePageable;
import fpt.sep490.repository.ProductImageRepository;
import fpt.sep490.repository.ProductRepository;
import fpt.sep490.service.ProductImageService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;
    private ModelMapper mapper;

    public ProductImageServiceImpl(ProductRepository productRepository, ProductImageRepository productImageRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.mapper = mapper;
    }


    @Override
    public ProductImageDto createProductImage(Long productId, ProductImageDto productImageDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        ProductImage image = new ProductImage();
        image.setImageUrl(productImageDto.getImageUrl());
        image.setProduct(product);

        ProductImage newImage = productImageRepository.save(image);
        return mapper.map(newImage, ProductImageDto.class);
    }

    @Override
    public ProductImageResponsePageable getAllProductImages(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ProductImage> images = productImageRepository.findAll(pageable);
        List<ProductImage> imageList = images.getContent();
        List<ProductImageDto> content = imageList.stream().map(image -> mapper.map(image, ProductImageDto.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(images.getNumber());
        pageableDto.setPageSize(images.getSize());
        pageableDto.setTotalElements(images.getTotalElements());
        pageableDto.setTotalPages(images.getTotalPages());
        pageableDto.setLast(images.isLast());

        ProductImageResponsePageable responsePageable = new ProductImageResponsePageable();
        responsePageable.setImages(content);
        responsePageable.setPage(pageableDto);
        return responsePageable;
    }

    @Override
    public ProductImageResponsePageable getProductImagesByProductId(Long productId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ProductImage> images = productImageRepository.getProductImagesByProductId(productId, pageable);
        List<ProductImage> imageList = images.getContent();
        List<ProductImageDto> content = imageList.stream().map(image -> mapper.map(image, ProductImageDto.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(images.getNumber());
        pageableDto.setPageSize(images.getSize());
        pageableDto.setTotalElements(images.getTotalElements());
        pageableDto.setTotalPages(images.getTotalPages());
        pageableDto.setLast(images.isLast());

        ProductImageResponsePageable responsePageable = new ProductImageResponsePageable();
        responsePageable.setImages(content);
        responsePageable.setPage(pageableDto);
        return responsePageable;
    }

    @Override
    public ProductImageDto getProductImageById(Long productId, Long productImageId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        ProductImage productImage = productImageRepository.findById(productImageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image", "id", productImageId));

        if(!productImage.getProduct().getId().equals(productId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Image doesn't  not belong to post");
        }

        return mapper.map(productImage, ProductImageDto.class);
    }

    @Override
    public ProductImageDto updateProductById(Long productId, Long productImageId, ProductImageDto productImageDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        ProductImage productImage = productImageRepository.findById(productImageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image", "id", productImageId));

        if(!productImage.getProduct().getId().equals(productId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Image doesn't  not belong to post");
        }

        productImage.setImageUrl(productImageDto.getImageUrl());
        ProductImage updateProductImage = productImageRepository.save(productImage);

        return mapper.map(updateProductImage, ProductImageDto.class);
    }

    @Override
    public void deleteProductById(Long productId, Long productImageId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        ProductImage productImage = productImageRepository.findById(productImageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image", "id", productImageId));

        if(!productImage.getProduct().getId().equals(productId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Image doesn't  not belong to post");
        }

        productImageRepository.delete(productImage);
    }
}
