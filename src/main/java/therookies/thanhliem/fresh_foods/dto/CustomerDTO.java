package therookies.thanhliem.fresh_foods.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @Min(value = 0,message = "Id should not be less than 0")
    private Long id;

    @NotBlank(message = "address is not blank")
    private String address;

    @NotBlank(message = "firstname is not blank")
    private String firstname;

    @NotBlank(message = "lastname is not blank")
    private String lastname;

    @NumberFormat
    private String phone;
    private Long userId;
}
