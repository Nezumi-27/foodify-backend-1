package fpt.sep490.payload;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponsePageable {
    private List<OrderResponse> orders;
    private PageableDto page;
}
