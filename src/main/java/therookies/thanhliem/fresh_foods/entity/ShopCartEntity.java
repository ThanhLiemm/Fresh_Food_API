package therookies.thanhliem.fresh_foods.entity;

import javax.persistence.*;

@Entity
@Table(name = "shopcart")
public class ShopCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "checked")
    boolean checked;

    @Column(name = "quantity")
    int quantity;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
