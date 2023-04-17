package fpt.sep490.payload;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class MerchantDto {
    private String merchantInfo;
    private String appUserName;
    private String amount;
    private String orderId;
}
