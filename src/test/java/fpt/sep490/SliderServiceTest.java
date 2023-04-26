package fpt.sep490;

import com.google.protobuf.Any;
import fpt.sep490.entity.Slider;
import fpt.sep490.payload.SliderDto;
import fpt.sep490.repository.SliderRepository;
import fpt.sep490.service.SliderService;
import fpt.sep490.service.impl.SliderServiceImpl;
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
public class SliderServiceTest {

    @Mock
    private SliderRepository sliderRepository;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private SliderServiceImpl sliderService;

    @Test
    public void stage1_testCreateSlider(){
        SliderDto sliderDto = new SliderDto(1L, "slider_test.png");
        Mockito.when(sliderRepository.save(Mockito.any(Slider.class))).thenReturn(mapper.map(sliderDto, Slider.class));

        SliderDto saveSlider = sliderService.createSlider(sliderDto);

        Assert.assertNotNull(saveSlider.getId());
        Assert.assertEquals("slider_test.png", saveSlider.getImageUrl());

        Mockito.verify(sliderRepository, Mockito.times(1)).save(Mockito.any(Slider.class));
    }

    @Test
    public void stage2_testGetAllSlider(){
        List<Slider> entities = new ArrayList<>();
        entities.add(new Slider(1L, "/sliders/1.png"));
        entities.add(new Slider(2L, "/sliders/2.png"));
        entities.add(new Slider(3L, "/sliders/3.png"));

        Mockito.when(sliderRepository.findAll()).thenReturn(entities);

        List<SliderDto> dtos = sliderService.getAllSliders();

        Assert.assertNotNull(dtos);
    }

    @Test
    public void stage3_testGetSliderById(){
        Slider slider = new Slider(1L, "slider_test.png");

        Mockito.when(sliderRepository.findById(1L)).thenReturn(Optional.of(slider));

        SliderDto sliderDto = sliderService.getSlider(1L);

        Assert.assertNotNull(sliderDto);
        Assert.assertEquals("slider_test.png", sliderDto.getImageUrl());
    }

    @Test
    public void stage4_testUpdateSliderById(){
        Slider slider = new Slider(1L, "slider_test.png");
        Mockito.when(sliderRepository.findById(1L)).thenReturn(Optional.of(slider));

        Slider sliderUpdated = new Slider(1L, "slider_test_updated.png");
        Mockito.when(sliderRepository.save(slider)).thenReturn(sliderUpdated);

        SliderDto sliderDtoUpdated = mapper.map(sliderUpdated, SliderDto.class);
        SliderDto sliderDto = sliderService.updateSlider(sliderDtoUpdated, 1L);

        Assert.assertNotNull(sliderDto);
        Assert.assertEquals("slider_test_updated.png", sliderDto.getImageUrl());

        Mockito.verify(sliderRepository, Mockito.times(1)).save(Mockito.any(Slider.class));
    }

    @Test
    public void stage5_testDeleteSlider(){
        Slider slider = new Slider(1L, "slider_test.png");

        Mockito.when(sliderRepository.findById(1L)).thenReturn(Optional.of(slider));

        sliderService.deleteSlider(1L);

        Mockito.verify(sliderRepository, Mockito.times(1)).delete(slider);
    }
}
