package fpt.sep490;

import fpt.sep490.entity.District;
import fpt.sep490.entity.Ward;
import fpt.sep490.payload.WardDto;
import fpt.sep490.repository.DistrictRepository;
import fpt.sep490.repository.WardRepository;
import fpt.sep490.service.impl.WardServiceImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WardServiceTest {
    @Mock
    private WardRepository wardRepository;

    @Mock
    private DistrictRepository districtRepository;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private WardServiceImpl wardService;

    @Test
    public void stage1_createWard(){
        District district = new District(1L, "DISTRICT_TEST", null);
        Ward ward = new Ward(1L,"WARD_TEST", district);
        WardDto wardDto = mapper.map(ward, WardDto.class);

        Mockito.when(districtRepository.findById(1L)).thenReturn(Optional.of(district));
        Mockito.when(wardRepository.save(Mockito.any(Ward.class))).thenReturn(ward);

        WardDto newWard = wardService.createWard(1L, wardDto);

        Assert.assertNotNull(newWard);
    }

    @Test
    public void stage2_testGetAllWardByDistrictId(){
        District district = new District(1L, "DISTRICT_TEST", null);
        List<Ward> wards = new ArrayList<>();
        wards.add(new Ward(1L, "Ward_1", district));
        wards.add(new Ward(2L, "Ward_2", district));
        wards.add(new Ward(3L, "Ward_3", district));

        Mockito.when(wardRepository.findByDistrictId(1L)).thenReturn(wards);

        List<WardDto> dtos = wardService.getAllWardsByDistrictId(1L);

        Assert.assertNotNull(dtos);
    }
}
