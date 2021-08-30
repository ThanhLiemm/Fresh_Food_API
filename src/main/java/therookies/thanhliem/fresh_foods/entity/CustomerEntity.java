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
@Table(name="Customer")
public class CustomerEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name="address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "customer")
    List<OrderEntity> orderdetails = new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<ShopCartEntity> shopcarts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
}
