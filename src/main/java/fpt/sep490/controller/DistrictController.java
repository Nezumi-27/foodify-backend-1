package fpt.sep490.controller;

import fpt.sep490.entity.District;
import fpt.sep490.payload.DistrictDto;
import fpt.sep490.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "CRUD REST APIs for District Resource")
@RestController
@RequestMapping("/api/districts")
public class DistrictController {
    private DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @ApiOperation(value = "Create new District")
    @PostMapping
    public ResponseEntity<DistrictDto> createDistrict(DistrictDto districtDto){
        return new ResponseEntity<>(districtService.createDistrict(districtDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All District")
    @GetMapping
    public ResponseEntity<List<DistrictDto>> getAllDistricts(){
        return ResponseEntity.ok(districtService.getAllDistricts());
    }

    @ApiOperation(value = "Get District by Id")
    @GetMapping("{id}")
    public ResponseEntity<DistrictDto> getDistrict(@PathVariable("id") Long id){
        return ResponseEntity.ok(districtService.getDistrict(id));
    }

    @ApiOperation(value = "Update District by Id")
    @PutMapping("{id}")
    public ResponseEntity<DistrictDto> updateDistrict(@RequestBody DistrictDto districtDto,
                                                      @PathVariable("id") Long id){
        return ResponseEntity.ok(districtService.updateDistrict(districtDto, id));
    }

    @ApiOperation(value = "Delete District by Id")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDistrict(@PathVariable("id") Long id){
        districtService.deleteDistrict(id);
        return ResponseEntity.ok("District deleted successfully");
    }
}
