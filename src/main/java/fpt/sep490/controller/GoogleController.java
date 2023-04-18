package fpt.sep490.controller;

import fpt.sep490.entity.map.Location;
import fpt.sep490.service.GeocodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/google")
public class GoogleController {
    private final GeocodeService geocodeService;

    public GoogleController(GeocodeService geocodeService) {
        this.geocodeService = geocodeService;
    }

    @ApiOperation("Test get location")
    @GetMapping
    public ResponseEntity<Location> getLocation(@RequestParam(value = "address") String address){
        return ResponseEntity.ok(geocodeService.getLocation(address));
    }
}
