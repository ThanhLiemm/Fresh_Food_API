package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);
    //ProductDTO update(ProductDTO productDTO);
    ProductDTO findById(Long id);
    List<ProductDTO> findAll();
    Map<String,String> delete(Long id);
    List<ProductDTO> findByCategoryId(Long id);
}
