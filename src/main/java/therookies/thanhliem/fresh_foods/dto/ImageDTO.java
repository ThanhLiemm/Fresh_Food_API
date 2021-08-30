package therookies.thanhliem.fresh_foods.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {

    @Min(value =0, message= "Id should not be less than 0")
    private Long id;

    @URL(message = "Url is invalid")
    private String url;

    private Long productId;
}
