package therookies.thanhliem.fresh_foods.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDTO {

    @Min(value = 0,message = "Id should not be less than 0")
    private Long id;

    @NotBlank(message="name can not blank")
    private String name;

    @NotNull(message = "Price is not null")
    @Min(value = 1,message="Price should not be less than 1 ")
    private float price;

    @Min(value =0,message="rating should not be less than 0")
    private int rating;

    @Min(value =0,message ="discount should not be less than 0")
    private int discount;

    private Long category_id;
    @NotBlank(message = "Description is not blank")

    private String description;

    @NotNull(message = "UnitType is not null")
    private String unitType;

    private ProductEntity.Status status;
    private String createdBy;
    private String modifiedBy;
    @Min(value = 1, message = "Quantity should not be less than 1")
    private int quantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date deadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate;

    @Size(min=2,max = 5, message = "Product must has two to five picture")
    private List<ImageDTO> listImage = new ArrayList<>();

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public ProductEntity.Status getStatus() {
        return status;
    }

    public void setStatus(ProductEntity.Status status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<ImageDTO> getListImage() {
        return listImage;
    }

    public void setListImge(List<ImageDTO> listImage) {
        this.listImage = listImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setListImage(List<ImageDTO> listImage) {
        this.listImage = listImage;
    }
}
