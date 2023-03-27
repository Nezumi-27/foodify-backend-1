package fpt.sep490.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    private Long id;

    @NotEmpty(message = "Shop name must not be empty")
    private String name;

    @NotEmpty(message = "Shop description must not be empty")
    private String description;

    @NotEmpty(message = "Image url must not be empty")
    private String imageUrl;

    @NotEmpty(message = "Enable status must not be empty")
    private Boolean isEnabled;

    @NotEmpty(message = "Student status must not be empty")
    private Boolean isStudent;

    @NotEmpty(message = "User must not be empty")
    private Long userId;

    @NotEmpty(message = "Shop name must not be empty")
    private String lat;

    @NotEmpty(message = "Shop name must not be empty")
    private String lng;
}
