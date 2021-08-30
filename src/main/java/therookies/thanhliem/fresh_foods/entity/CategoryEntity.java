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
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

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

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products= new ArrayList<>();

    public CategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity category = (CategoryEntity) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        return name != null ? name.equals(category.name) : category.name == null;
    }

}
