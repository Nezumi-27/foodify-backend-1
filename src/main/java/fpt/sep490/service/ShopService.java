package fpt.sep490.service;

import fpt.sep490.payload.ShopDto;
import fpt.sep490.payload.ShopResponse;

public interface ShopService {
    ShopDto createShop(ShopDto shopDto);

    ShopResponse getAllShops(int pageNo, int pageSize, String sortBy, String sortDir);

    ShopDto getShopById(Long shopId);

    ShopDto updateShop(Long shopId, ShopDto shopDto);

    void deleteShop(Long shopId);
}
