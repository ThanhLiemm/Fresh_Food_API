package therookies.thanhliem.fresh_foods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import therookies.thanhliem.fresh_foods.dto.ProductDTO;
import therookies.thanhliem.fresh_foods.entity.UserEntity;
import therookies.thanhliem.fresh_foods.repository.UserRepository;
import therookies.thanhliem.fresh_foods.service.IProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value="/product")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO model){
        model.setId(0L);
        return productService.save(model);
    }

    @PutMapping(value="/product/{id}")
    public ProductDTO updateProduct(@Valid @RequestBody ProductDTO model, @PathVariable("id") long id){
        model.setId(id);
        return productService.save(model);
    }
    @GetMapping(value="/product/{id}")
    public ProductDTO getProduct(@PathVariable("id") long id){
        return productService.findById(id);
    }

    @GetMapping(value="/product/all")
    public List<ProductDTO> getAllProduct(){
        return productService.findAll();
    }

    @GetMapping(value="/product")
    public List<ProductDTO> getByCategoryId(@RequestParam(name="categoryId") Long id) {
        return productService.findByCategoryId(id);
    }
    @DeleteMapping(value="/product/{id}")
    public Map<String,String> deleteProduct(@PathVariable(value = "id") Long id) {
        return productService.delete(id);
    }
}
