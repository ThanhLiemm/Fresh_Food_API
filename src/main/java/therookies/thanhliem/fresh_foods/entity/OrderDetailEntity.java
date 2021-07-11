package therookies.thanhliem.fresh_foods.entity;

import javax.persistence.*;

@Entity
@Table(name="orderdetail")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name="orderdetail_id")
    private OrderEntity order;
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


    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
