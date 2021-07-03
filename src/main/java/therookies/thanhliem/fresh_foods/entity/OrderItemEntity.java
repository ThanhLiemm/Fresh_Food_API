package therookies.thanhliem.fresh_foods.entity;

import javax.persistence.*;

@Entity
@Table(name="orderitem")
public class OrderItemEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="quantity")
    private int quantity;
    @OneToOne
    @JoinColumn(name="product_id")
    private ProductEntity item;
    @ManyToOne
    @JoinColumn(name="orderdetail_id")
    private OrderDetailEntity orderdetail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductEntity getItem() {
        return item;
    }

    public void setItem(ProductEntity item) {
        this.item = item;
    }

    public OrderDetailEntity getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(OrderDetailEntity orderdetail) {
        this.orderdetail = orderdetail;
    }
}
