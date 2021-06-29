package therookies.thanhliem.fresh_foods.entity;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Customer")
public class CustomerEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="email")
    private String email;
    @Column(name="firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name="address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "customer")
    List<OrderDetailEntity> orderdetails = new ArrayList<>();
    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderDetailEntity> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<OrderDetailEntity> orderdetails) {
        this.orderdetails = orderdetails;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
