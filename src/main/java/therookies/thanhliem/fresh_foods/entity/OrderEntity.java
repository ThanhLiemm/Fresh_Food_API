package therookies.thanhliem.fresh_foods.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity {
    public enum Status {
            DELIVERING,DELIVERED,CANCEL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="total")
    private Float total;

    @Column(length = 11,name = "status")
    private Status status;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="fullname")
    private String fullname;

    @CreatedBy
    @Column(name="createdby")
    private String createdBy;

    @CreatedDate
    @Column(name ="createddate")
    private Date createdDate;

    @LastModifiedBy
    @Column(name="modifiedby")
    private String modifiedBy;

    @LastModifiedDate
    @Column(name="modifieddate")
    private Date modifiedDate;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderDetailEntity> orderitems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name="payment_id")
    private PaymentEntity payment;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(getClass()!= obj.getClass() && obj == null) return false;
        OrderEntity orderEntity = (OrderEntity) obj;
        return id.equals(orderEntity.id);
    }
}
