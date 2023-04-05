package fpt.sep490.service;

import fpt.sep490.payload.ShopDto;
import fpt.sep490.payload.ShopResponse;
import fpt.sep490.payload.ShopResponsePageable;

public interface ShopService {
    ShopDto createShop(ShopDto shopDto);

    ShopResponsePageable getAllShops(int pageNo, int pageSize, String sortBy, String sortDir);

    ShopResponsePageable getAllEnabledShops(int pageNo, int pageSize, String sortBy, String sortDir);

    ShopResponse getShopById(Long shopId);

    ShopResponse getShopByUserId(Long userId);

    ShopDto updateShop(Long shopId, ShopDto shopDto);

    void deleteShop(Long shopId);
}
