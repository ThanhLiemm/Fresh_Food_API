package therookies.thanhliem.fresh_foods.dto.pageable;

import therookies.thanhliem.fresh_foods.dto.CategoryDTO;
import therookies.thanhliem.fresh_foods.dto.ProductDTO;

import java.util.List;

public class OutputDTO {
    int totalPage;
    int page;
    int totalProduct;
    List<ProductDTO> listProduct;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ProductDTO> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductDTO> listProduct) {
        this.listProduct = listProduct;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }
}
