package therookies.thanhliem.fresh_foods.service;

import org.springframework.data.domain.Pageable;
import therookies.thanhliem.fresh_foods.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);
    //ProductDTO update(ProductDTO productDTO);
    ProductDTO findById(Long id);
    ProductDTO findByIdCusomer(Long id);
    List<ProductDTO> findAll();
    Map<String,String> delete(Long id);
    List<ProductDTO> findByCategoryId(Long id);
  //customer pageable
    List<ProductDTO> findByCategoryId(Long id, Pageable pageable);
    List<ProductDTO> findAll(Pageable pageable);
    int totalProduct();
    int totalProduct(Long id);
    //admin pageable
    List<ProductDTO> findByCategoryIdAdmin(Long id,Pageable pageable);
    List<ProductDTO> findAllAdmin(Pageable pageable);
    int totalProductAdmin();
    int totalProductAdmin(Long id);
    //top 10 best discount
    List<ProductDTO> top10Discount();
    
    
}
