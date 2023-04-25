package fpt.sep490.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long userId;
    private Long shopId;
    private String userRole;
    private String userEmail;
}
