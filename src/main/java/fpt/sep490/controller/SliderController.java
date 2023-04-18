package fpt.sep490.controller;

import fpt.sep490.payload.SliderDto;
import fpt.sep490.service.SliderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "CRUD REST APIs for Slider Resource")
@RestController
@RequestMapping("/api/sliders")
public class SliderController {
    private SliderService sliderService;

    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create new Slider")
    @PostMapping
    public ResponseEntity<SliderDto> createSlider(@RequestBody SliderDto sliderDto){
        return new ResponseEntity<>(sliderService.createSlider(sliderDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Sliders")
    @GetMapping
    public ResponseEntity<List<SliderDto>> getAllSliders(){
        return ResponseEntity.ok(sliderService.getAllSliders());
    }

    @ApiOperation(value = "Get Slider by Id")
    @GetMapping("{id}")
    public ResponseEntity<SliderDto> getSlider(@PathVariable("id") Long id){
        return ResponseEntity.ok(sliderService.getSlider(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Slider by Id")
    @PutMapping("{id}")
    public ResponseEntity<SliderDto> updateSlider(@RequestBody SliderDto sliderDto,
                                                  @PathVariable("id") Long id){
        return ResponseEntity.ok(sliderService.updateSlider(sliderDto, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Slider by Id")
    @DeleteMapping()
    public ResponseEntity<String> deleteSlider(@RequestParam("id") Long id){
        sliderService.deleteSlider(id);
        return ResponseEntity.ok("Slider deleted successfully");
    }

}
