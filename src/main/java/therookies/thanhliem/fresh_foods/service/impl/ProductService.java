package therookies.thanhliem.fresh_foods.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import therookies.thanhliem.fresh_foods.dto.ProductDTO;
import therookies.thanhliem.fresh_foods.entity.ImageEntity;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.CategoryRepository;
import therookies.thanhliem.fresh_foods.repository.ProductRepository;
import therookies.thanhliem.fresh_foods.service.IProductService;
import therookies.thanhliem.fresh_foods.entity.ProductEntity.Status;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    public ProductService(ModelMapper modelMapper) {
        this.mapper = modelMapper;
        this.mapper.typeMap(ProductEntity.class,ProductDTO.class).addMapping(src ->src.getCategory().getId(),ProductDTO::setCategory_id);
        this.mapper.typeMap(ProductEntity.class,ProductDTO.class).addMapping(ProductEntity::getImages,ProductDTO::setListImge);
//        this.mapper.typeMap(ImageDTO.class,ImageEntity.class).addMapping(ImageDTO::getUrl,ImageEntity::setUrl);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        if(!categoryRepository.existsById(productDTO.getCategory_id()))
            throw new IdNotFoundException("Can not found category = "+productDTO.getCategory_id());

        ProductEntity productEntity = productRepository.findById(productDTO.getId()) //check id
                    .map(product -> { //update
                        product.setName(productDTO.getName());
                        product.setPrice(productDTO.getPrice());
                        product.setRating(productDTO.getRating());
                        product.setDiscount(productDTO.getDiscount());
                        product.setDescription(productDTO.getDescription());
                        product.setUnitType(productDTO.getUnitType());
                        product.setQuantity(productDTO.getQuantity());
                        product.setStatus(productDTO.getStatus());
                        product.setDeadline(productDTO.getDeadline());
                        return productRepository.save(product);
                    }).orElseGet(() -> { //create new with id
                        return mapper.map(productDTO, ProductEntity.class);
                    });
        //get list image
        ProductEntity finalProductEntity = productEntity;
        List<ImageEntity> listImage = productDTO.getListImage().stream().map(
                image->{
                    ImageEntity imageEntity =  mapper.map(image,ImageEntity.class);
                    imageEntity.setProduct(finalProductEntity);
                    return imageEntity;
                }
        ).collect(Collectors.toList());

            productEntity.setImages(listImage);
            productEntity.setCategory(categoryRepository.getById(productDTO.getCategory_id()));
            productEntity = productRepository.save(productEntity);
            return mapper.map(productEntity, ProductDTO.class);
        }

    @Override
    public ProductDTO findById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .map(product ->{return product;})
                .orElseThrow(()->{throw new IdNotFoundException("Not Found Product id = "+id);});
        ProductDTO productDTO = mapper.map(productEntity,ProductDTO.class);
        return productDTO;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return mapper.map(productEntities,new TypeToken<List<ProductDTO>>() {}.getType());
    }

    @Override
    public Map<String, String> delete(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(()->{throw new IdNotFoundException("Can not found product id = "+id);});
        productRepository.delete(productEntity);
        Map<String, String> response = new HashMap<>();
        response.put("status","Success");
        return response;
    }

    @Override
    public List<ProductDTO> findByCategoryId(Long id) {
        if(!categoryRepository.existsById(id))
            throw new IdNotFoundException("Can not found category id = "+id);
        List<ProductEntity> productEntities = productRepository.getAllByCategoryId(id);
        return mapper.map(productEntities,new TypeToken<List<ProductDTO>>() {}.getType());
    }

}

