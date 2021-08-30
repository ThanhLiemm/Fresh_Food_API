package therookies.thanhliem.fresh_foods.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    @Min(value =1, message = "Id should no be less than 1")
    private Long id;

    @NotBlank(message = "Name is not blank")
    private String name;

    @URL
    private String url;
}
