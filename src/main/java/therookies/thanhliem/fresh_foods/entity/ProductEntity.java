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
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity{
    public ProductEntity() {

    }
    public enum Status {
        INACTIVE,ACTIVE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="price")
    private float price;
    @Column(name="discount")
    private int discount;
    @Column(name="rating")
    private int rating;
    @Enumerated(EnumType.STRING)
    @Column(length = 8,name = "status")
    private Status status;
    @Column(name = "deadline")
    private Date deadline;
    @Column(name="unittype")
    private String unitType;
    @Column(name="quantity")
    private int quantity;
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
    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ImageEntity> images = new ArrayList<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ShopCartEntity> shopcarts = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public List<OrderDetailEntity> getOrderDetailEntities() {
        return orderDetailEntities;
    }

    public void setOrderDetailEntities(List<OrderDetailEntity> orderDetailEntities) {
        this.orderDetailEntities = orderDetailEntities;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quanlity) {
        this.quantity = quanlity;
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

    public ProductEntity(Long id, String name, String description, float price, int discount,
                         int rating, Status status, Date deadline, String unitType, int quantity, CategoryEntity category,
                         List<ImageEntity> images, List<OrderDetailEntity> orderDetailEntities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.status = status;
        this.deadline = deadline;
        this.unitType = unitType;
        this.quantity = quantity;
        this.category = category;
        this.images = images;
        this.orderDetailEntities = orderDetailEntities;
    }

    public List<ShopCartEntity> getShopcarts() {
        return shopcarts;
    }

    public void setShopcarts(List<ShopCartEntity> shopcarts) {
        this.shopcarts = shopcarts;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != getClass()) return false;
        ProductEntity productEntity = (ProductEntity) obj;
        return id.equals(productEntity.id);
    }
}
