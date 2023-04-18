package fpt.sep490.service.impl;

import fpt.sep490.entity.map.GoogleResponse;
import fpt.sep490.entity.map.Location;
import fpt.sep490.entity.map.Result;
import fpt.sep490.service.GeocodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class GeocodeServiceImpl implements GeocodeService {

    private String apiKey = "QUl6YVN5QVkxNEljMzJVUDI2SGc2R0lMem5PZmJCaWhpWTVCVXh3";

    private final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json";


    @Override
    public Location getLocation(String address) {
        byte[] decodeBytes = Base64.getDecoder().decode(apiKey);
        String decodeKey = new String(decodeBytes);

        RestTemplate restTemplate = new RestTemplate();

        String url = String.format("%s?address=%s&key=%s", BASE_URL, address, decodeKey);

        GoogleResponse googleResponse = restTemplate.getForObject(url, GoogleResponse.class);

        if(googleResponse.getStatus().equals("OK")){
            return googleResponse.getResults().get(0).getGeometry().getLocation();
        }
        else{
            throw new RuntimeException("Error getting location for address: " + address);
        }
    }
}
