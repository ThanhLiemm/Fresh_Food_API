package therookies.thanhliem.fresh_foods.dto;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
