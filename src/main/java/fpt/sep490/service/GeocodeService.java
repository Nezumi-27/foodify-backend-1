package fpt.sep490.service;

import fpt.sep490.entity.map.Location;

public interface GeocodeService {

    Location getLocation(String address);
}
