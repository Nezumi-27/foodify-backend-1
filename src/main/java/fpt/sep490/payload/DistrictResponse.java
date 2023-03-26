package fpt.sep490.payload;

import lombok.Data;

import java.util.Set;

@Data
public class DistrictResponse {
    private Long id;
    private String name;
    private Set<WardDto> wards;
}
