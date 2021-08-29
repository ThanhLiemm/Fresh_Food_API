package therookies.thanhliem.fresh_foods.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="order_details")
public class OrderDetailEntity implements Serializable {

    @EmbeddedId
    private OrderDetailId id;


    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private Float price;

    @Column(name="discount")
    private Integer discount;

    @Column(name = "deadline")
    private Date deadline;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderdetail_id")
    private OrderEntity order;

    public OrderDetailEntity(int quantity, float price, int discount, Date deadline, ProductEntity product, OrderEntity order) {
        this.id = new OrderDetailId(product.getId(),order.getId());
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.deadline = deadline;
        this.product = product;
        this.order = order;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class OrderDetailId implements Serializable {
        @Column(name="product_id")
        private Long productId;

        @Column(name="orderdetail_id")
        private Long orderId;

        @Override
        public int hashCode() {
            return Objects.hash(productId,orderId);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false ;
            OrderDetailId orderDetailId = (OrderDetailId) obj;
            return productId.equals(orderDetailId.productId) &&
                    orderId.equals(orderDetailId.orderId);
        }
}

}
