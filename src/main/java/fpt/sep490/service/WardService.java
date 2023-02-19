package fpt.sep490.service;

import fpt.sep490.payload.WardDto;

import java.util.List;

public interface WardService {
    WardDto createWard(long districtId, WardDto wardDto);
    List<WardDto> getAllWardsByDistrictId(Long districtId);
    WardDto getWardById(Long districtId, Long wardId);
    WardDto updateWard(Long districtId, Long wardId, WardDto wardRequest);
    void deleteWard(Long districtId, Long wardId);
}
