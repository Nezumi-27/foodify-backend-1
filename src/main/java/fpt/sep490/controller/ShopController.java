package fpt.sep490.controller;

import fpt.sep490.payload.ShopDto;
import fpt.sep490.payload.ShopResponse;
import fpt.sep490.payload.ShopResponsePageable;
import fpt.sep490.service.impl.ShopServiceImpl;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api("CRUD APIs for Shop Resources")
@RestController
@RequestMapping("/api/shops")
public class ShopController {
    private ShopServiceImpl shopService;

    public ShopController(ShopServiceImpl shopService) {
        this.shopService = shopService;
    }

    @ApiOperation("Create Shop")
    @PostMapping
    public ResponseEntity<ShopDto> createShop(ShopDto shopDto){
        return new ResponseEntity<>(shopService.createShop(shopDto), HttpStatus.CREATED);
    }

    @ApiOperation("Get all Shops")
    @GetMapping
    public ShopResponsePageable getAllShops(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_SHOP_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return shopService.getAllShops(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Get Shop by Id")
    @GetMapping("{id}")
    public ResponseEntity<ShopResponse> getShopById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(shopService.getShopById(id));
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Update Shop by Id")
    @PutMapping("{id}")
    public ResponseEntity<ShopDto> updateShop(@RequestBody ShopDto shopDto, @PathVariable(name = "id") Long shopId){
        return ResponseEntity.ok(shopService.updateShop(shopId, shopDto));
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Delete Shop by Id")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteShop(@PathVariable(name = "id") Long shopId){
        shopService.deleteShop(shopId);
        return ResponseEntity.ok("Shop deleted successfully");
    }
}
