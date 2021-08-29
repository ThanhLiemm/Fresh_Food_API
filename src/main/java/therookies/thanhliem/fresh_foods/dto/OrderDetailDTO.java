package therookies.thanhliem.fresh_foods.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    @Min(value = 1,message ="Quantity should not be less than 1")
    private int quantity;
    private Long orderId;
    @Min(value = 0,message = "Id should not be less than 0")
    private Long productId;
    private Float price;
    private Integer discount;
    private Date deadline;
}
