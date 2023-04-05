package fpt.sep490.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WardDto {
    private Long id;

    @NotEmpty(message = "Ward name must not be empty")
    private String name;
}
