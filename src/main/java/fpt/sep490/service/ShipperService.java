package fpt.sep490.service;

import fpt.sep490.payload.ShipperDto;
import fpt.sep490.payload.ShipperResponsePageable;

public interface ShipperService {
    ShipperDto createShipper(ShipperDto shipperDto);
    ShipperResponsePageable getAllShipper(int pageNo, int pageSize, String sortBy, String sortDir);
    ShipperDto getShipperById(Long shipperId);
    ShipperDto updateShipper(Long shipperId, Boolean isShipping);
    void deleteShipper(Long shipperId);
}
