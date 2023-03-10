package fpt.sep490.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SliderDto {
    private Long id;

    @NotEmpty(message = "Image url must not be empty")
    private String imageUrl;
}
