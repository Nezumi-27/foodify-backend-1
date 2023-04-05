package fpt.sep490.service;

import fpt.sep490.payload.SliderDto;

import java.util.List;

public interface SliderService {
    SliderDto createSlider(SliderDto sliderDto);

    List<SliderDto> getAllSliders();

    SliderDto getSlider(Long sliderId);

    SliderDto updateSlider(SliderDto sliderDto, Long sliderId);

    void deleteSlider(Long sliderId);
}
