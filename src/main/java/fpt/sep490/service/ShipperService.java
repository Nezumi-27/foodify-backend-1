package fpt.sep490.service;

import fpt.sep490.payload.ShipperDto;
import fpt.sep490.payload.ShipperResponse;

public interface ShipperService {
    ShipperDto createShipper(ShipperDto shipperDto);
    ShipperResponse getAllShipper(int pageNo, int pageSize, String sortBy, String sortDir);
    ShipperDto getShipperById(Long shipperId);
    ShipperDto updateShipper(Long shipperId, ShipperDto shipperDto);
    void deleteShipper(Long shipperId);
}
