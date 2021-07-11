package therookies.thanhliem.fresh_foods.dto;

import javax.validation.constraints.Min;

public class OrderDetailDTO {
    @Min(value = 0,message = "Id should not be less than 0")
    private Long id;
    @Min(value = 1,message ="Quantity should not be less than 1")
    private int quantity;
    private Long orderId;
    @Min(value = 0,message = "Id should not be less than 0")
    private Long productId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
