package fpt.sep490.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    @NotEmpty(message = "Transaction ID must not be null")
    private String id;

    @NotEmpty(message = "User full name must not be null")
    private String userFullName;

    @NotEmpty(message = "Product cost must not be null")
    private Long productCost;

    @NotEmpty(message = "Shipping cost must not be null")
    private Long shippingCost;

    @NotEmpty(message = "Total must not be null")
    private Long total;

    @JsonFormat(pattern="HH:mm dd-MM-yyyy", timezone = "GMT+7")
    private Timestamp createdTime;
}
