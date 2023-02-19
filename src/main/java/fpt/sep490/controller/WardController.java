package fpt.sep490.controller;

import fpt.sep490.payload.DistrictDto;
import fpt.sep490.payload.WardDto;
import fpt.sep490.service.WardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "CRUD REST APIs for Ward Resources")
@RestController
@RequestMapping("/api")
public class WardController {
    private WardService wardService;

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @ApiOperation(value = "Create Ward by District Id")
    @PostMapping("/districts/{districtId}/wards")
    public ResponseEntity<WardDto> createWard(@PathVariable(value = "districtId") Long districtId,
                                              @RequestBody WardDto wardDto){
        return new ResponseEntity<>(wardService.createWard(districtId, wardDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Wards by District Id")
    @GetMapping("/districts/{districtId}/wards")
    public List<WardDto> getWardsByDistrictId(@PathVariable(value = "districtId") Long districtId){
        return wardService.getAllWardsByDistrictId(districtId);
    }

    @ApiOperation(value = "Get Wards by Id")
    @GetMapping("/districts/{districtId}/wards/{wardId}")
    public ResponseEntity<WardDto> getWardsById(@PathVariable(value = "districtId") Long districtId,
                                      @PathVariable(value = "wardId") Long wardId){
        WardDto wardDto = wardService.getWardById(districtId, wardId);
        return new ResponseEntity<>(wardDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Ward by Id")
    @PutMapping("/districts/{districtId}/wards/{wardId}")
    public ResponseEntity<WardDto> updateWard(@PathVariable(value = "districtId") Long districtId,
                                              @PathVariable(value = "wardId") Long wardId,
                                              @RequestBody WardDto wardDto){
        WardDto updateWard = wardService.updateWard(districtId, wardId, wardDto);
        return new ResponseEntity<>(updateWard, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Ward by Id")
    @DeleteMapping("/districts/{districtId}/wards/{wardId}")
    public ResponseEntity<String> updateWard(@PathVariable(value = "districtId") Long districtId,
                                              @PathVariable(value = "wardId") Long wardId){
        wardService.deleteWard(districtId, wardId);
        return ResponseEntity.ok("Ward delete successfully");
    }
}
