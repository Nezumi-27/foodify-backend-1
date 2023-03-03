package fpt.sep490.service.impl;

import fpt.sep490.entity.Shipper;
import fpt.sep490.entity.Shop;
import fpt.sep490.entity.User;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.PageableDto;
import fpt.sep490.payload.ShipperDto;
import fpt.sep490.payload.ShipperResponse;
import fpt.sep490.payload.ShipperResponsePageable;
import fpt.sep490.repository.ShipperRepository;
import fpt.sep490.repository.ShopRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.ShipperService;
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
        shipper.setIsShipping(shipperDto.getIsShipping());
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
    public ShipperDto getShipperById(Long shipperId) {
        Shipper shipper = shipperRepository.findById(shipperId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipper", "id", shipperId));
        return mapper.map(shipper, ShipperDto.class);
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
