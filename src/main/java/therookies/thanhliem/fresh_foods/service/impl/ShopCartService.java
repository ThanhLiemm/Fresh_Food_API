package therookies.thanhliem.fresh_foods.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import therookies.thanhliem.fresh_foods.dto.ShopCartDTO;
import therookies.thanhliem.fresh_foods.entity.CustomerEntity;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;
import therookies.thanhliem.fresh_foods.entity.ShopCartEntity;
import therookies.thanhliem.fresh_foods.entity.ShopCartEntity.ShopCartId;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.CustomerRepository;
import therookies.thanhliem.fresh_foods.repository.ProductRepository;
import therookies.thanhliem.fresh_foods.repository.ShopCartRepository;
import therookies.thanhliem.fresh_foods.service.IShopCartService;

import java.util.List;

@Service
public class ShopCartService implements IShopCartService {
    @Autowired
    ShopCartRepository repository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper mapper;

    public ShopCartService (ModelMapper modelMapper) {
        this.mapper = modelMapper;
        this.mapper.typeMap(ShopCartDTO.class, ShopCartEntity.class).addMapping(ShopCartDTO::isChecked,
                ShopCartEntity::setChecked);
//        this.mapper.typeMap(ShopCartId.class,ShopCartDTO.class).addMapping(ShopCartId::getProduct,ShopCartDTO::setProduct);
//        this.mapper.typeMap(ShopCartId.class,ShopCartDTO.class).addMapping(src-> src.getCustomer().getId(),ShopCartDTO::setCustomerId);
    }
    @Override
    public ShopCartDTO save(ShopCartDTO dto) {

//        ShopCartEntity entity = mapper.map(dto, ShopCartEntity.class);

        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(()->{throw new IdNotFoundException("Can't not found customer");});
        ProductEntity product = productRepository.findById(dto.getProduct().getId())
                .orElseThrow(()->{throw new IdNotFoundException("Can't not found product");});


        ShopCartEntity entity = repository.findById(new ShopCartId(customer.getId(),product.getId()))
                .orElseGet( () -> {return null;});
        if(entity!= null)
            entity.setQuantity(entity.getQuantity()+dto.getQuantity());  //automatic increase quantity if it is one product
        else entity = mapper.map(dto, ShopCartEntity.class);
        entity.setId(new ShopCartId(customer.getId(),product.getId()));
        entity = repository.save(entity);
        ShopCartDTO response = mapper.map(entity,ShopCartDTO.class);
        return response;
    }

    @Override
    public List<ShopCartDTO> update(ShopCartDTO dto) {

        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(()->{throw new IdNotFoundException("Can't not found customer");});
        ProductEntity product = productRepository.findById(dto.getProduct().getId())
                .orElseThrow(()->{throw new IdNotFoundException("Can't not found product");});

        ShopCartEntity entity = repository.findById(new ShopCartId(customer.getId(),product.getId()))
                .orElseThrow( () -> {throw new IdNotFoundException("Can't not found item in shop cart");});

        entity.setQuantity(dto.getQuantity());
        entity.setChecked(dto.isChecked());
        repository.save(entity);

        List<ShopCartEntity> list = repository.findByCustomerIdOrderByIdDesc(customer.getId());
        return mapper.map(list, new TypeToken<List<ShopCartDTO>>() {
        }.getType());
    }

    @Override
    public List<ShopCartDTO> delete(Long customerId, Long productId) {

        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(()->{throw new IdNotFoundException("Can't not found customer");});
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(()->{throw new IdNotFoundException("Can't not found product");});
        ShopCartId id = new ShopCartId(customer.getId(),product.getId()) ;
        ShopCartEntity entity = repository.findById(id)
                .orElseThrow(()->{throw new IdNotFoundException("can not found item");});
        repository.delete(entity);
        return getAll(id.getCustomerId());
    }

    @Override
    public List<ShopCartDTO> getAll(Long customerId) {
        List<ShopCartEntity> list = repository.findByCustomerIdOrderByIdDesc(customerId);
        return mapper.map(list, new TypeToken<List<ShopCartDTO>>() {
        }.getType());
    }
}
