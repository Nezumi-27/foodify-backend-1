package fpt.sep490;

import fpt.sep490.entity.District;
import fpt.sep490.payload.DistrictDto;
import fpt.sep490.repository.DistrictRepository;
import fpt.sep490.service.DistrictService;
import fpt.sep490.service.impl.DistrictServiceImpl;
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
public class DistrictServiceTest {

    @Mock
    private DistrictRepository districtRepository;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private DistrictServiceImpl districtService;

    private District district = new District(1L, "test_district");

    @Test
    public void stage1_testCreateDistrict(){
        Mockito.when(districtRepository.save(Mockito.any(District.class))).thenReturn(district);

        DistrictDto newDistrictDto = districtService.createDistrict(mapper.map(district, DistrictDto.class));

        Assert.assertNotNull(newDistrictDto);
        Assert.assertEquals("test_district", newDistrictDto.getName());
    }

    @Test
    public void stage2_testGetAllDistricts(){
        List<District> list = new ArrayList<>();
        list.add(district);
        list.add(new District(2L, "test_district2"));
        list.add(new District(3L, "test_district3"));

        Mockito.when(districtRepository.findAll()).thenReturn(list);

        List<DistrictDto> listDtos = districtService.getAllDistricts();

        Assert.assertNotNull(listDtos);
    }

    @Test
    public void stage3_testGetDistrictById(){
        Mockito.when(districtRepository.findById(1L)).thenReturn(Optional.of(district));

        DistrictDto districtGET = districtService.getDistrict(1L);

        Assert.assertNotNull(districtGET);
        Assert.assertEquals("test_district", districtGET.getName());
    }

    @Test
    public void stage4_testUpdateDistrict(){
        DistrictDto updateDto = new DistrictDto(1L, "update");
        District districtUpdate = mapper.map(updateDto, District.class);

        Mockito.when(districtRepository.findById(1L)).thenReturn(Optional.of(district));
        Mockito.when(districtRepository.save(Mockito.any(District.class))).thenReturn(districtUpdate);

        DistrictDto districtDto = districtService.updateDistrict(updateDto, 1L);

        Assert.assertNotNull(districtUpdate);
        Assert.assertEquals("update", districtDto.getName());
    }

    @Test
    public void stage5_deleteUpdateDistrict(){
        Mockito.when(districtRepository.findById(1L)).thenReturn(Optional.of(district));

        districtService.deleteDistrict(1L);

        Mockito.verify(districtRepository, Mockito.times(1)).delete(district);
    }
}
