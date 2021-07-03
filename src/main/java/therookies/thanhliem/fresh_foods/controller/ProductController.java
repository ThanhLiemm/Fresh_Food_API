package therookies.thanhliem.fresh_foods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import therookies.thanhliem.fresh_foods.dto.ProductDTO;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping(value="/product")
    public ProductDTO createProduct(@RequestBody ProductDTO model){
        return productService.save(model);
    }

    @PutMapping(value="/product/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO model, @PathVariable("id") long id){
        model.setId(id);
        return productService.save(model);
    }
    @GetMapping(value="/product/{id}")
    public ProductDTO getProduct(@PathVariable("id") long id){
        ProductDTO productDTO = productService.findById(id);
        if( productDTO == null) {
            throw new IdNotFoundException(id, new ProductEntity());
        }
        return productService.findById(id);
    }

    @GetMapping(value="/product/")
    public List<ProductDTO> getAllProduct(){
        return productService.findAll();
    }
}
