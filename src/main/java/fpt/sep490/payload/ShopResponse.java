package fpt.sep490.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ShopResponse {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Boolean isEnabled;
    private Boolean isStudent;
    private UserResponse user;
    private String lat;
    private String lng;
}
