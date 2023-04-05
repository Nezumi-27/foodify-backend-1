package fpt.sep490.service;

import fpt.sep490.payload.AddressDto;
import fpt.sep490.payload.AddressResponsePageable;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDto);

    AddressResponsePageable getAllAddresses(int pageNo, int pageSize, String sortBy, String sortDir);

    AddressDto getAddressById(Long addressId);

    AddressDto updateAddress(Long addressId, AddressDto addressDto);

    void deleteAddress(Long addressId);
}
