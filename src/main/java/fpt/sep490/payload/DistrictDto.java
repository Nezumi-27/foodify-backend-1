package fpt.sep490.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDto {
    @NotEmpty(message = "District id must not be empty")
    private Long id;

    @NotEmpty(message = "District's name must not be empty")
    private String name;
}
