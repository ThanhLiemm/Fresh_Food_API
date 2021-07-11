package therookies.thanhliem.fresh_foods.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PaymentDTO {
    @Min(value =1, message = "Id should no be less than 1")
    private Long id;
    @NotBlank(message = "Name is not blank")
    private String name;
    @URL
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
