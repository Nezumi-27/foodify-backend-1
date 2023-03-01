package fpt.sep490.payload;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailResponsePageable {
    private List<OrderDetailResponse> orderDetails;
    private PageableDto page;
}
