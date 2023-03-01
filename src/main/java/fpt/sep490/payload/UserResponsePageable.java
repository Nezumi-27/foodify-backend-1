package fpt.sep490.payload;

import lombok.Data;

import java.util.List;

@Data
public class UserResponsePageable {
    private List<UserResponse> users;
    private PageableDto page;
}
