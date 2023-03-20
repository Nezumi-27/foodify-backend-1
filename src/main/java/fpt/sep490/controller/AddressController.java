package fpt.sep490.controller;

import fpt.sep490.payload.AddressDto;
import fpt.sep490.payload.AddressResponse;
import fpt.sep490.service.AddressService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(value = "CRUD APIs for Address Resources")
@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Create Address")
    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto){
        return new ResponseEntity<>(addressService.createAddress(addressDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation("Get all Addresses")
    @GetMapping
    public AddressResponse getAllAddress(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ADDRESS_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return addressService.getAllAddresses(pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Get Address by Id")
    @GetMapping("{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable(value = "id") Long addressId){
        return ResponseEntity.ok(addressService.getAddressById(addressId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Update Address by Id")
    @PutMapping("{id}")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto, @PathVariable(value = "id") Long addressId){
        return ResponseEntity.ok(addressService.updateAddress(addressId, addressDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Delete Address by Id")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable(value = "id") Long addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully!");
    }
}
