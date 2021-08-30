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
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity{

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

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != getClass()) return false;
        ProductEntity productEntity = (ProductEntity) obj;
        return id.equals(productEntity.id);
    }
}
