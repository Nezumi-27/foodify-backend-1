package fpt.sep490.service.impl;

import fpt.sep490.entity.Address;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.AddressDto;
import fpt.sep490.payload.AddressResponse;
import fpt.sep490.payload.PageableDto;
import fpt.sep490.repository.AddressRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private ModelMapper mapper;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = mapper.map(addressDto, Address.class);
        addressRepository.save(address);
        return mapper.map(address, AddressDto.class);
    }

    @Override
    public AddressResponse getAllAddresses(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Address> addresses = addressRepository.findAll(pageable);

        List<Address> listOfAddress = addresses.getContent();
        List<AddressDto> content =listOfAddress.stream().map(address -> mapper.map(address, AddressDto.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(addresses.getNumber());
        pageableDto.setPageSize(addresses.getSize());
        pageableDto.setTotalElements(addresses.getTotalElements());
        pageableDto.setTotalPages(addresses.getTotalPages());
        pageableDto.setLast(addresses.isLast());

        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setAddresses(content);
        addressResponse.setPage(pageableDto);

        return addressResponse;
    }


    @Override
    public AddressDto getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
         return mapper.map(address, AddressDto.class);
    }

    @Override
    public AddressDto updateAddress(Long addressId, AddressDto addressDto) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
        address.setAddress(addressDto.getAddress());
        address.setDistrict(addressDto.getDistrict());
        address.setWard(addressDto.getWard());

        Address updateAddress = addressRepository.save(address);
        return mapper.map(updateAddress, AddressDto.class);
    }

    @Override
    public void deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
        addressRepository.delete(address);
    }

}
