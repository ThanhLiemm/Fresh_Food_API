package therookies.thanhliem.fresh_foods.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import therookies.thanhliem.fresh_foods.entity.ProductEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
