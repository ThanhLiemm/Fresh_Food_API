package therookies.thanhliem.fresh_foods.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @Min(value = 0,message = "Id should not less than 0")
    private Long id;

    @NotBlank(message = "Category name is not blank")
    private String name;
}
