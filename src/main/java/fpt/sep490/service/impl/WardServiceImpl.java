package fpt.sep490.service.impl;

import fpt.sep490.entity.District;
import fpt.sep490.entity.Ward;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.WardDto;
import fpt.sep490.repository.DistrictRepository;
import fpt.sep490.repository.WardRepository;
import fpt.sep490.service.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WardServiceImpl implements WardService {
    private DistrictRepository districtRepository;
    private WardRepository wardRepository;
    private ModelMapper mapper;

    public WardServiceImpl(DistrictRepository districtRepository, WardRepository wardRepository, ModelMapper mapper) {
        this.districtRepository = districtRepository;
        this.wardRepository = wardRepository;
        this.mapper = mapper;
    }

    @Override
    public WardDto createWard(long districtId, WardDto wardDto) {
        Ward ward = mapper.map(wardDto, Ward.class);

        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId));

        ward.setDistrict(district);
        Ward newWard = wardRepository.save(ward);

        return mapper.map(newWard, WardDto.class);
    }

    @Override
    public List<WardDto> getAllWardsByDistrictId(Long districtId) {
        List<Ward> wards = wardRepository.findByDistrictId(districtId);

        return wards.stream().map(ward -> mapper.map(ward, WardDto.class)).collect(Collectors.toList());
    }

    @Override
    public WardDto getWardById(Long districtId, Long wardId) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId));

        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new ResourceNotFoundException("Ward", "id", wardId));

        if(!ward.getDistrict().getId().equals(district.getId())){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Ward does not belong to District");
        }

        return mapper.map(ward, WardDto.class);
    }

    @Override
    public WardDto updateWard(Long districtId, Long wardId, WardDto wardRequest) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId));

        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new ResourceNotFoundException("Ward", "id", wardId));

        if(!ward.getDistrict().getId().equals(district.getId())){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Ward does not belong to District");
        }

        ward.setName(wardRequest.getName());
        Ward updateWard = wardRepository.save(ward);
        return mapper.map(ward, WardDto.class);
    }

    @Override
    public void deleteWard(Long districtId, Long wardId) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId));

        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new ResourceNotFoundException("Ward", "id", wardId));

        if(!ward.getDistrict().getId().equals(district.getId())){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Ward does not belong to District");
        }

        wardRepository.delete(ward);
    }
}
