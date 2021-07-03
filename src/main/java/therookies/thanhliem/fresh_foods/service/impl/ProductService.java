package therookies.thanhliem.fresh_foods.service.impl;

import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import therookies.thanhliem.fresh_foods.dto.ProductDTO;
import therookies.thanhliem.fresh_foods.entity.CategoryEntity;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.CategoryRepository;
import therookies.thanhliem.fresh_foods.repository.ProductRepository;
import therookies.thanhliem.fresh_foods.service.IProductService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        if (productDTO.getId() == null) { //create
            productEntity = productRepository.save(mapper.map(productDTO, ProductEntity.class));
            return mapper.map(productEntity, ProductDTO.class);
        } else {
            productEntity = productRepository.findById(productDTO.getId()) //check id
                    .map(product -> {
                        ProductEntity rp = mapper.map(productDTO, ProductEntity.class);
                        return productRepository.save(rp);
                    }).orElseGet(()->{
                        return productRepository.save(mapper.map(productDTO, ProductEntity.class));
                    });
            return mapper.map(productEntity, ProductDTO.class);
        }
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductEntity productEntity = null;
        productEntity = productRepository.findOneById(id);
        if(productEntity==null) return null;
        return mapper.map(productEntity,ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductEntity> productEntities = new ArrayList<ProductEntity>();
        productEntities = productRepository.findAll();
        return mapper.map(productEntities,new TypeToken<List<ProductDTO>>() {}.getType());
    }
}
