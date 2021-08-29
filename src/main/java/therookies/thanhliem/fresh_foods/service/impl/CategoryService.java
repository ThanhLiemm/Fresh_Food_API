package therookies.thanhliem.fresh_foods.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import therookies.thanhliem.fresh_foods.dto.CategoryDTO;
import therookies.thanhliem.fresh_foods.entity.CategoryEntity;
import therookies.thanhliem.fresh_foods.exception.CanNotChangeDB;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.CategoryRepository;
import therookies.thanhliem.fresh_foods.service.ICategoryService;
import therookies.thanhliem.fresh_foods.entity.ProductEntity.Status;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper ;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) throws RuntimeException{

        CategoryEntity categoryEntity  = categoryRepository.findById(categoryDTO.getId())
                    .map(category ->{ //update
                        category.setName(categoryDTO.getName());
                        return category;
                    })
                    .orElseGet(()->{ //create
                        return mapper.map(categoryDTO,CategoryEntity.class);
                    });
        categoryEntity = categoryRepository.save(categoryEntity);
        return mapper.map(categoryEntity,CategoryDTO.class);
    }

    @Override
    public CategoryDTO findById(Long id) throws RuntimeException{
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(()->{
                    throw new IdNotFoundException("Can not found category id = " +id);
                });
        return mapper.map(categoryEntity,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findAll() throws RuntimeException{
        List<CategoryEntity> listCategoryEntity =  categoryRepository.findAll();
        return mapper.map(listCategoryEntity,new TypeToken<List<CategoryDTO>>() {}.getType());
    }

    @Override
    public Map<String, String> delete(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Can not found category id = " + id));
        if(category.getProducts().size()!=0)
            throw new CanNotChangeDB("Category had product can not delete");
        categoryRepository.delete(category);
        Map<String, String> respone = new HashMap<>();
        respone.put("Status", "Success");
        return respone;
    }
}
