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
@AllArgsConstructor
@Entity
@Table(name = "shopcart")
public class ShopCartEntity implements Serializable {

    @EmbeddedId
    private ShopCartId id;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "checked")
    boolean checked;

    @Column(name = "quantity")
    int quantity;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class ShopCartId implements Serializable {
        @Column(name = "customer_id")
        private Long customerId;

        @Column(name = "product_id")
        private Long productId;

        @Override
        public int hashCode() {
            return Objects.hash(customerId,productId);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null && obj.getClass()!= getClass()) return false;
            ShopCartId shopCartId = (ShopCartId) obj;
            return customerId.equals(shopCartId.customerId) &&
                    productId.equals(shopCartId.productId);
        }
    }
}
