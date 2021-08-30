package therookies.thanhliem.fresh_foods.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity payment = (PaymentEntity) o;

        if (id != null ? !id.equals(payment.id) : payment.id != null) return false;
        return name != null ? name.equals(payment.name) : payment.name == null;
    }
}
