package therookies.thanhliem.fresh_foods.dto;

import org.springframework.lang.Nullable;
import therookies.thanhliem.fresh_foods.entity.ProductEntity.Status;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
    @Min(value = 0,message = "Id should not less than 0")
    private Long id;
    @NotBlank(message = "Category name is not blank")
    private String name;
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

}
