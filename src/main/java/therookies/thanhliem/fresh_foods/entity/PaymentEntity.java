package therookies.thanhliem.fresh_foods.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="payment")
public class PaymentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="url")
    private String url;
    @OneToMany(mappedBy = "payment")
    List<OrderDetailEntity> orderdetails = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<OrderDetailEntity> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<OrderDetailEntity> orderdetails) {
        this.orderdetails = orderdetails;
    }
}
