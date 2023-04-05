package fpt.sep490.service.impl;

import fpt.sep490.entity.District;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.DistrictDto;
import fpt.sep490.payload.DistrictResponse;
import fpt.sep490.repository.DistrictRepository;
import fpt.sep490.service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {
    private DistrictRepository districtRepository;
    private ModelMapper mapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapper mapper) {
        this.districtRepository = districtRepository;
        this.mapper = mapper;
    }

    @Override
    public DistrictDto createDistrict(DistrictDto districtDto) {
        District district = mapper.map(districtDto, District.class);
        District savedDistrict = districtRepository.save(district);

        return mapper.map(savedDistrict, DistrictDto.class);
    }

    @Override
    public List<DistrictResponse> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        return districts.stream().map((district -> mapper.map(district, DistrictResponse.class)))
                .collect(Collectors.toList());
    }

    @Override
    public DistrictResponse getDistrict(Long districtId) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId));

        return mapper.map(district, DistrictResponse.class);
    }

    @Override
    public DistrictDto updateDistrict(DistrictDto districtDto, Long districtId) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId));

        district.setName(districtDto.getName());
        District updateDistrict = districtRepository.save(district);
        return mapper.map(updateDistrict, DistrictDto.class);
    }

    @Override
    public void deleteDistrict(Long districtId) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId));

        districtRepository.delete(district);
    }
}
