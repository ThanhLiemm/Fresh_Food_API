package therookies.thanhliem.fresh_foods.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import therookies.thanhliem.fresh_foods.entity.OrderEntity.Status;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @Min(message = "must-be-greater-than-or-equals-0", value = 0)
    private Long id;

    @NotBlank(message = "address is not blank")
    private String address;

    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message="Fullname is not blank")
    private String fullname;

    @NumberFormat
    private String phone;

    private Status status;

    private float total;

    @NotNull (message = "Payment not null")
    private Long paymentId;

    private Long customerId;

    private String createdBy;

    private String modifiedBy;

    List<OrderDetailDTO> orderDetailDTOS ;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate;

}
