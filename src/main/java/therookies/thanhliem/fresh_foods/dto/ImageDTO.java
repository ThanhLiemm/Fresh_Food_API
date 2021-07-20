package therookies.thanhliem.fresh_foods.dto;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;

public class ImageDTO {
    @Min(value =0, message= "Id should not be less than 0")
    private Long id;
    @URL(message = "Url is invalid")
    private String url;
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ImageDTO(Long id, String url, Long productId) {
        this.id = id;
        this.url = url;
        this.productId = productId;
    }

    public ImageDTO() {
    }
}
