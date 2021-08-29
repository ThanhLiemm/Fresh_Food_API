package therookies.thanhliem.fresh_foods.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopCartDTO {
    private int quantity;
    private boolean checked;
    private ProductDTO product;
    private Long customerId;
}
