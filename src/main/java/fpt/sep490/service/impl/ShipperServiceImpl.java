package fpt.sep490.service.impl;

import fpt.sep490.entity.Product;
import fpt.sep490.entity.Shipper;
import fpt.sep490.entity.Shop;
import fpt.sep490.entity.User;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.*;
import fpt.sep490.repository.ShipperRepository;
import fpt.sep490.repository.ShopRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.ShipperService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShipperServiceImpl implements ShipperService {
    private ShipperRepository shipperRepository;
    private UserRepository userRepository;
    private ShopRepository shopRepository;
    private ModelMapper mapper;

    public ShipperServiceImpl(ShipperRepository shipperRepository, UserRepository userRepository, ShopRepository shopRepository, ModelMapper mapper) {
        this.shipperRepository = shipperRepository;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
        this.mapper = mapper;
    }

    @Override
    public ShipperDto createShipper(ShipperDto shipperDto) {
        User user = userRepository.findById(shipperDto.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", shipperDto.getUserId()));

        if(!user.getRole().getName().equals("ROLE_SHIPPER")){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This user don't have ROLE_SHIPPER");
        }

        Shop shop = shopRepository.findById(shipperDto.getShopId())
                .orElseThrow(()-> new ResourceNotFoundException("Shop", "id", shipperDto.getShopId()));

        Shipper shipper = new Shipper();
        shipper.setIsShipping(false);
        shipper.setIsActive(false);
        shipper.setUser(user);
        shipper.setShop(shop);

        return mapper.map(shipperRepository.save(shipper), ShipperDto.class);
    }

    @Override
    public ShipperResponsePageable getAllShipper(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Shipper> shippers = shipperRepository.findAll(pageable);
        List<Shipper> shipperList = shippers.getContent();
        List<ShipperResponse> content = shipperList.stream().map(shipper -> mapper.map(shipper, ShipperResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(shippers.getNumber());
        pageableDto.setPageSize(shippers.getSize());
        pageableDto.setTotalElements(shippers.getTotalElements());
        pageableDto.setTotalPages(shippers.getTotalPages());
        pageableDto.setLast(shippers.isLast());

        ShipperResponsePageable shipperResponsePageable = new ShipperResponsePageable();
        shipperResponsePageable.setShippers(content);
        shipperResponsePageable.setPage(pageableDto);
        return shipperResponsePageable;
    }

    @Override
    public ShipperResponsePageable getShippersByShop(Long shopId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Shipper> shippers = shipperRepository.findShippersByShop(shop, pageable);
        List<Shipper> shipperList = shippers.getContent();
        List<ShipperResponse> content = shipperList.stream().map(shipper -> mapper.map(shipper, ShipperResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(shippers.getNumber());
        pageableDto.setPageSize(shippers.getSize());
        pageableDto.setTotalElements(shippers.getTotalElements());
        pageableDto.setTotalPages(shippers.getTotalPages());
        pageableDto.setLast(shippers.isLast());

        ShipperResponsePageable shipperResponsePageable = new ShipperResponsePageable();
        shipperResponsePageable.setShippers(content);
        shipperResponsePageable.setPage(pageableDto);
        return shipperResponsePageable;
    }

    @Override
    public ShipperResponsePageable findShipperByName(String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<Shipper> alls = shipperRepository.findAll();
        List<Shipper> shippers = new ArrayList<>();

        for(Shipper shipper : alls){
            if(shipper.getUser().getFullName().toUpperCase().contains(name.toUpperCase())) shippers.add(shipper);
        }

        int start = pageNo * pageSize;
        int end = Math.min(start + pageSize, shippers.size());

        List<Shipper> shipperList = shippers.subList(start, end);
        Page<Shipper> page = new PageImpl<>(shipperList, pageable, shippers.size());
        List<ShipperResponse> content = shipperList.stream().map(shipper -> mapper.map(shipper, ShipperResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(page.getNumber());
        pageableDto.setPageSize(page.getSize());
        pageableDto.setTotalElements(page.getTotalElements());
        pageableDto.setTotalPages(page.getTotalPages());
        pageableDto.setLast(page.isLast());

        ShipperResponsePageable responses = new ShipperResponsePageable();
        responses.setShippers(content);
        responses.setPage(pageableDto);
        return responses;
    }

    @Override
    public List<ShipperResponse> findFreeShipperByShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));

        List<Shipper> shippers = shipperRepository.findShippersByShopAndIsActiveAndAndIsShipping(shop, true, false);
        return shippers.stream().map(shipper -> mapper.map(shipper, ShipperResponse.class)).collect(Collectors.toList());
    }

    @Override
    public ShipperResponsePageable findShopShipperByName(Long shopId, String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<Shipper> alls = shipperRepository.findShippersByShop(shop);
        List<Shipper> shippers = new ArrayList<>();

        for(Shipper shipper : alls){
            if(shipper.getUser().getFullName().toUpperCase().contains(name.toUpperCase())) shippers.add(shipper);
        }

        int start = pageNo * pageSize;
        int end = Math.min(start + pageSize, shippers.size());

        List<Shipper> shipperList = shippers.subList(start, end);
        Page<Shipper> page = new PageImpl<>(shipperList, pageable, shippers.size());
        List<ShipperResponse> content = shipperList.stream().map(shipper -> mapper.map(shipper, ShipperResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(page.getNumber());
        pageableDto.setPageSize(page.getSize());
        pageableDto.setTotalElements(page.getTotalElements());
        pageableDto.setTotalPages(page.getTotalPages());
        pageableDto.setLast(page.isLast());

        ShipperResponsePageable responses = new ShipperResponsePageable();
        responses.setShippers(content);
        responses.setPage(pageableDto);
        return responses;
    }

    @Override
    public ShipperResponse getShipperById(Long shipperId) {
        Shipper shipper = shipperRepository.findById(shipperId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipper", "id", shipperId));
        return mapper.map(shipper, ShipperResponse.class);
    }

    @Override
    public ShipperResponse getShipperByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Shipper shipper = shipperRepository.findShipperByUser(user)
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This user is not a shipper"));
        return mapper.map(shipper, ShipperResponse.class);
    }

    @Override
    public ShipperDto updateShipperShippingStatus(Long shipperId, Boolean isShipping) {
        Shipper shipper = shipperRepository.findById(shipperId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipper", "id", shipperId));

        shipper.setIsShipping(isShipping);
        Shipper updateShipper = shipperRepository.save(shipper);

        return mapper.map(updateShipper, ShipperDto.class);
    }

    @Override
    public ShipperDto swapShipperStatus(Long shipperId, Boolean isActive) {
        Shipper shipper = shipperRepository.findById(shipperId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipper", "id", shipperId));

        if(isActive) shipper.setIsShipping(false);
        shipper.setIsActive(isActive);
        Shipper updateShipper = shipperRepository.save(shipper);

        return mapper.map(updateShipper, ShipperDto.class);
    }

    @Override
    public void deleteShipper(Long shipperId) {
        Shipper shipper = shipperRepository.findById(shipperId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipper", "id", shipperId));
        shipperRepository.delete(shipper);
    }
}
