package fpt.sep490.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;

    @NotEmpty(message = "Role name must not be empty")
    private String roleName;
}
