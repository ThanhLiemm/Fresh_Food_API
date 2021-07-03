package therookies.thanhliem.fresh_foods.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orderdetail")
@EntityListeners(AuditingEntityListener.class)
public class OrderDetailEntity {
    public enum Status {
            DELIVERING,DELIVERED,CANCEL;
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
    @Column(name="createby")
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
    @OneToMany(mappedBy = "orderdetail")
    List<OrderItemEntity> orderitems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name ="user_id")
    private CustomerEntity customer;
    @ManyToOne
    @JoinColumn(name="payment_id")
    private PaymentEntity payment;

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<OrderItemEntity> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<OrderItemEntity> orderitems) {
        this.orderitems = orderitems;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
