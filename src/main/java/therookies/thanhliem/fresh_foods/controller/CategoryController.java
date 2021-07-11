package therookies.thanhliem.fresh_foods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import therookies.thanhliem.fresh_foods.dto.CategoryDTO;
import therookies.thanhliem.fresh_foods.entity.CategoryEntity;
import therookies.thanhliem.fresh_foods.service.ICategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @PostMapping(value="/category")
    public CategoryDTO createCategory(@Valid @RequestBody CategoryDTO model) {
        model.setId(0L);
        return categoryService.save(model);
    }
    @PutMapping(value="/category/{id}")
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO model, @PathVariable("id") Long id) {
        model.setId(id);
        return categoryService.save(model);
    }
    @GetMapping(value="/category")
    public List<CategoryDTO> findAll() {
        return categoryService.findAll();
    }
    @GetMapping(value="/category/{id}")
    public CategoryDTO findOneCategory(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }
    @DeleteMapping(value = "/category/{id}")
    public Map<String,String> changeCategoryStatus(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }

}
