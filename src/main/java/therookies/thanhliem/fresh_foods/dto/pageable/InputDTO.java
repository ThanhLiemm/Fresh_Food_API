package therookies.thanhliem.fresh_foods.dto.pageable;

import javax.validation.constraints.Min;

public class InputDTO {
    @Min(value = 0,message = "Page should not be less than 0")
    int page;
    @Min(value = 0,message = "Limit should not be less than 0")
    int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
