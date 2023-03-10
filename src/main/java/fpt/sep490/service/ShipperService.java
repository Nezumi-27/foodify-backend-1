package fpt.sep490.service;

import fpt.sep490.payload.ShipperDto;
import fpt.sep490.payload.ShipperResponsePageable;

public interface ShipperService {
    ShipperDto createShipper(ShipperDto shipperDto);
    ShipperResponsePageable getAllShipper(int pageNo, int pageSize, String sortBy, String sortDir);
    ShipperDto getShipperById(Long shipperId);
    ShipperDto updateShipperShippingStatus(Long shipperId, Boolean isShipping);
    ShipperDto swapShipperStatus(Long shipperId, Boolean isActive);
    void deleteShipper(Long shipperId);
}
