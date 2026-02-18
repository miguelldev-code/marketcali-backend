package miguel.sales.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SaleItemRequest {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
