package fpt.sep490.controller;

import fpt.sep490.payload.ShipperDto;
import fpt.sep490.payload.ShipperResponse;
import fpt.sep490.payload.ShipperResponsePageable;
import fpt.sep490.service.ShipperService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api("CRUD APIs for Shippers Resource")
@RestController
@RequestMapping("/api/shippers")
public class ShipperController {
    private final ShipperService shipperService;

    public ShipperController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Create Shipper")
    @PostMapping
    public ResponseEntity<ShipperDto> createShipper(@RequestBody ShipperDto shipperDto){
        return new ResponseEntity<>(shipperService.createShipper(shipperDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Get All Shippers")
    @GetMapping
    public ShipperResponsePageable getAllShippers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_SHIPPER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return shipperService.getAllShipper(pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Get All Shippers By Shop")
    @GetMapping("/shop/{shopId}")
    public ShipperResponsePageable getAllShippersByShop(
            @PathVariable(value = "shopId") Long shopId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_SHIPPER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return shipperService.getShippersByShop(shopId,pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Search Shippers By Name")
    @GetMapping("/search")
    public ShipperResponsePageable findShippersByName(
            @RequestParam(value = "shipperName") String shipperName,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_SHIPPER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return shipperService.findShipperByName(shipperName, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Search Shop Shippers By Name")
    @GetMapping("/shop/{shopId}/search")
    public ShipperResponsePageable findShopShippersByName(
            @PathVariable(value = "shopId") Long shopId,
            @RequestParam(value = "shipperName") String shipperName,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_SHIPPER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return shipperService.findShopShipperByName(shopId, shipperName, pageNo, pageSize, sortBy, sortDir);
    }


    @ApiOperation("Get Shipper By Id")
    @GetMapping("/{id}")
    public ResponseEntity<ShipperResponse> getShipperById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(shipperService.getShipperById(id));
    }

    @ApiOperation("Get Shipper By User Id")
    @GetMapping("/user/{id}")
    public ResponseEntity<ShipperResponse> getShipperByUserId(@PathVariable(value = "id") Long userId){
        return ResponseEntity.ok(shipperService.getShipperByUserId(userId));
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP') || hasRole('SHIPPER')")
    @ApiOperation("Update Shipper Shipping Status by Id")
    @PutMapping("/{id}/shipping")
    public ResponseEntity<ShipperDto> updateShipperShippingStatus(@PathVariable(value = "id") Long id,
                                                    @RequestParam Boolean isShipping){
        return ResponseEntity.ok(shipperService.updateShipperShippingStatus(id, isShipping));
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP') || hasRole('SHIPPER')")
    @ApiOperation("Update Shipper Active Status by Id")
    @PutMapping("/{id}/active")
    public ResponseEntity<ShipperDto> updateShipperActiveStatus(@PathVariable(value = "id") Long id,
                                                    @RequestParam Boolean isActive){
        return ResponseEntity.ok(shipperService.swapShipperStatus(id, isActive));
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP')")
    @ApiOperation("Delete Shipper by Id")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteShipper(@PathVariable(value = "id") Long id){
        shipperService.deleteShipper(id);
        return ResponseEntity.ok("Shipper delete successfully");
    }
}
