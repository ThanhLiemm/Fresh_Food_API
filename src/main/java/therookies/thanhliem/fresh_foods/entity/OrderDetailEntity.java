package therookies.thanhliem.fresh_foods.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orderdetail")
public class OrderDetailEntity extends BaseEntity{
    @Column(name="total")
    private Float total;
    @Column(name="status")
    private int status;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="fullname")
    private String fullname;
    @OneToMany(mappedBy = "orderdetail")
    List<OrderItemEntity> orderitems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name ="user_id")
    private CustomerEntity customer;
    @ManyToOne
    @JoinColumn(name="payment_id")
    private PaymentEntity payment;
}
