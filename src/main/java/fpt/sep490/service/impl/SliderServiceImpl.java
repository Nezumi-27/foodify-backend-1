package fpt.sep490.service.impl;

import fpt.sep490.entity.Slider;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.SliderDto;
import fpt.sep490.repository.SliderRepository;
import fpt.sep490.service.SliderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SliderServiceImpl implements SliderService {
    private SliderRepository sliderRepository;
    private ModelMapper mapper;

    public SliderServiceImpl(SliderRepository sliderRepository, ModelMapper mapper) {
        this.sliderRepository = sliderRepository;
        this.mapper = mapper;
    }

    @Override
    public SliderDto createSlider(SliderDto sliderDto) {
        Slider slider = mapper.map(sliderDto, Slider.class);
        sliderRepository.save(slider);
        return mapper.map(slider, SliderDto.class);
    }

    @Override
    public List<SliderDto> getAllSliders() {
        List<Slider> sliders =sliderRepository.findAll();
        return sliders.stream().map((slider -> mapper.map(slider, SliderDto.class))).collect(Collectors.toList());
    }

    @Override
    public SliderDto getSlider(Long sliderId) {
        Slider slider = sliderRepository.findById(sliderId)
                .orElseThrow(() -> new ResourceNotFoundException("Slider", "id", sliderId));
        return mapper.map(slider, SliderDto.class);
    }

    @Override
    public SliderDto updateSlider(SliderDto sliderDto, Long sliderId) {
        Slider slider = sliderRepository.findById(sliderId)
                .orElseThrow(() -> new ResourceNotFoundException("Slider", "id", sliderId));
        slider.setImageUrl(sliderDto.getImageUrl());
        Slider updateSlider = sliderRepository.save(slider);
        return mapper.map(updateSlider, SliderDto.class);
    }

    @Override
    public void deleteSlider(Long sliderId) {
        Slider slider = sliderRepository.findById(sliderId)
                .orElseThrow(() -> new ResourceNotFoundException("Slider", "id", sliderId));
        sliderRepository.delete(slider);
    }
}
