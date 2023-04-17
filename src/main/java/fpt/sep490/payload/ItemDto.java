package fpt.sep490.payload;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String name;
    private String price;
    private String quantity;
}
