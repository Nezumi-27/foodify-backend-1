package fpt.sep490.service;

import fpt.sep490.payload.DistrictDto;
import fpt.sep490.payload.SliderDto;

import java.util.List;

public interface DistrictService {
    DistrictDto createDistrict(DistrictDto districtDto);

    List<DistrictDto> getAllDistricts();

    DistrictDto getDistrict(Long districtId);

    DistrictDto updateDistrict(DistrictDto districtDto, Long districtId);

    void deleteDistrict(Long districtId);
}
