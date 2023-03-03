package fpt.sep490.controller;

import fpt.sep490.payload.ShipperDto;
import fpt.sep490.payload.ShipperResponsePageable;
import fpt.sep490.service.ShipperService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("CRUD APIs for Shippers Resource")
@RestController
@RequestMapping("/api/shippers")
public class ShipperController {
    private final ShipperService shipperService;

    public ShipperController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @ApiOperation("Create Shipper")
    @PostMapping
    public ResponseEntity<ShipperDto> createShipper(ShipperDto shipperDto){
        return new ResponseEntity<>(shipperService.createShipper(shipperDto), HttpStatus.CREATED);
    }

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

    @ApiOperation("Get Shipper By Id")
    @GetMapping("/{id}")
    public ResponseEntity<ShipperDto> getShipperById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(shipperService.getShipperById(id));
    }

    @ApiOperation("Update Shipper Shipping Status by Id")
    @PutMapping("/{id}/shipping")
    public ResponseEntity<ShipperDto> updateShipperShippingStatus(@PathVariable(value = "id") Long id,
                                                    @RequestParam Boolean isShipping){
        return ResponseEntity.ok(shipperService.updateShipperShippingStatus(id, isShipping));
    }

    @ApiOperation("Update Shipper Active Status by Id")
    @PutMapping("/{id}/active")
    public ResponseEntity<ShipperDto> updateShipperActiveStatus(@PathVariable(value = "id") Long id,
                                                    @RequestParam Boolean isActive){
        return ResponseEntity.ok(shipperService.swapShipperStatus(id, isActive));
    }

    @ApiOperation("Delete Shipper by Id")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteShipper(@PathVariable(value = "id") Long id){
        shipperService.deleteShipper(id);
        return ResponseEntity.ok("Shipper delete successfully");
    }
}
