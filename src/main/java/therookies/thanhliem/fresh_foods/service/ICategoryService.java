package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.CategoryDTO;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    CategoryDTO save(CategoryDTO categoryDTO);
    CategoryDTO findById(Long id);
    List<CategoryDTO> findAll();
    Map<String,String> delete(Long id);
}
