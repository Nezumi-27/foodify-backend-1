package fpt.sep490.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    private Long id;
    private String description;
    private String imageUrl;
    private Boolean isEnabled;
    private Boolean isStudent;
    private Long userId;
}
