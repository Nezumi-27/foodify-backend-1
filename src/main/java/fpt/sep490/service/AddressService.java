package fpt.sep490.service;

import fpt.sep490.payload.AddressDto;
import fpt.sep490.payload.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDto);

    AddressResponse getAllAddresses(int pageNo, int pageSize, String sortBy, String sortDir);

    AddressDto getAddressById(Long addressId);

    AddressDto updateAddress(Long addressId, AddressDto addressDto);

    void deleteAddress(Long addressId);
}
