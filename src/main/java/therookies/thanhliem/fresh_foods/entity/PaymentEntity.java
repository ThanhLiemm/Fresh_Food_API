package therookies.thanhliem.fresh_foods.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="payment")
public class PaymentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="url")
    private String url;
    @OneToMany(mappedBy = "payment")
    List<OrderEntity> orderdetails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<OrderEntity> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<OrderEntity> orderdetails) {
        this.orderdetails = orderdetails;
    }
}
