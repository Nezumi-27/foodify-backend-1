package fpt.sep490.service.impl;

import fpt.sep490.entity.Address;
import fpt.sep490.entity.Product;
import fpt.sep490.entity.Shop;
import fpt.sep490.entity.User;
import fpt.sep490.entity.map.Location;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.PageableDto;
import fpt.sep490.payload.ShopDto;
import fpt.sep490.payload.ShopResponse;
import fpt.sep490.payload.ShopResponsePageable;
import fpt.sep490.repository.ProductRepository;
import fpt.sep490.repository.ShopRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.GeocodeService;
import fpt.sep490.service.ShopService;
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
public class ShopServiceImpl implements ShopService {
    private GeocodeService geocodeService;
    private ShopRepository shopRepository;
    private ModelMapper mapper;
    private UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShopServiceImpl(GeocodeService geocodeService, ShopRepository shopRepository, ModelMapper mapper, UserRepository userRepository,
                           ProductRepository productRepository) {
        this.geocodeService = geocodeService;
        this.shopRepository = shopRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ShopDto createShop(ShopDto shopDto) {
        User user = userRepository.findById(shopDto.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", shopDto.getUserId()));

        if(!user.getRole().getName().equals("ROLE_SHOP")){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This user don't have shop role");
        }

        Address address = user.getAddresses().stream().findFirst().get();
        String addressString = address.getAddress() + ", " + address.getWard() + ", " + address.getDistrict() + ", Đà Nẵng";

        Location shopLocation = geocodeService.getLocation(addressString);

        Shop shop = mapper.map(shopDto, Shop.class);
        shop.setUser(user);
        shop.setLat(shopLocation.getLat());
        shop.setLng(shopLocation.getLng());
        Shop newShop = shopRepository.save(shop);
        return mapper.map(newShop, ShopDto.class);
    }

    @Override
    public ShopResponsePageable getAllShops(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Shop> shops = shopRepository.findAll(pageable);

        List<Shop> listOfShops = shops.getContent();
        List<ShopDto> content = listOfShops.stream().map(shop -> mapper.map(shop, ShopDto.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(shops.getNumber());
        pageableDto.setPageSize(shops.getSize());
        pageableDto.setTotalElements(shops.getTotalElements());
        pageableDto.setTotalPages(shops.getTotalPages());
        pageableDto.setLast(shops.isLast());

        ShopResponsePageable shopResponsePageable = new ShopResponsePageable();
        shopResponsePageable.setShops(content);
        shopResponsePageable.setPage(pageableDto);
        return shopResponsePageable;
    }

    @Override
    public ShopResponsePageable findShopsByName(String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Shop> shops = shopRepository.findShopsByNameContaining(name, pageable);

        List<Shop> listOfShops = shops.getContent();
        List<ShopDto> content = listOfShops.stream().map(shop -> mapper.map(shop, ShopDto.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(shops.getNumber());
        pageableDto.setPageSize(shops.getSize());
        pageableDto.setTotalElements(shops.getTotalElements());
        pageableDto.setTotalPages(shops.getTotalPages());
        pageableDto.setLast(shops.isLast());

        ShopResponsePageable shopResponsePageable = new ShopResponsePageable();
        shopResponsePageable.setShops(content);
        shopResponsePageable.setPage(pageableDto);
        return shopResponsePageable;
    }

    @Override
    public ShopResponsePageable getAllEnabledShops(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Shop> shops = shopRepository.findShopsByIsEnabled(true, pageable);

        List<Shop> listOfShops = shops.getContent();
        List<ShopDto> content = listOfShops.stream().map(shop -> mapper.map(shop, ShopDto.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(shops.getNumber());
        pageableDto.setPageSize(shops.getSize());
        pageableDto.setTotalElements(shops.getTotalElements());
        pageableDto.setTotalPages(shops.getTotalPages());
        pageableDto.setLast(shops.isLast());

        ShopResponsePageable shopResponsePageable = new ShopResponsePageable();
        shopResponsePageable.setShops(content);
        shopResponsePageable.setPage(pageableDto);
        return shopResponsePageable;
    }

    @Override
    public ShopResponse getShopById(Long shopId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));
        return mapper.map(shop, ShopResponse.class);
    }

    @Override
    public ShopResponse getShopByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Shop shop = shopRepository.findShopByUser(user)
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This user don't have shop"));

        return mapper.map(shop, ShopResponse.class);
    }

    @Override
    public ShopDto updateShop(Long shopId, ShopDto shopDto) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));
        shop.setName(shopDto.getName());
        shop.setDescription(shopDto.getDescription());
        shop.setImageUrl(shopDto.getImageUrl());
        shop.setIsStudent(shopDto.getIsStudent());
        shop.setIsEnabled(shopDto.getIsEnabled());
        shop.setLat(shopDto.getLat());
        shop.setLng(shopDto.getLng());
        if(shopDto.getIsEnabled() == false){
            List<Product> products = productRepository.findProductsByShop(shop);
            for(Product product : products){
                product.setIsEnabled(false);
                productRepository.save(product);
            }
        }
        else {
            List<Product> products = productRepository.findProductsByShop(shop);
            for(Product product : products){
                product.setIsEnabled(true);
                productRepository.save(product);
            }
        }

        Shop updateShop = shopRepository.save(shop);

        return mapper.map(updateShop, ShopDto.class);
    }

    @Override
    public void deleteShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));
        shopRepository.delete(shop);
    }
}
