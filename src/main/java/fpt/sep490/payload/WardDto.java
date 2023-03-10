package fpt.sep490.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WardDto {
    private Long id;

    @NotEmpty(message = "Ward name must not be empty")
    private String name;
}
