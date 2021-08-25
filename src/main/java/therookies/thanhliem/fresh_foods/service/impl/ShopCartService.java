package therookies.thanhliem.fresh_foods.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import therookies.thanhliem.fresh_foods.dto.ShopCartDTO;
import therookies.thanhliem.fresh_foods.entity.CustomerEntity;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;
import therookies.thanhliem.fresh_foods.entity.ShopCartEntity;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.CustomerRepository;
import therookies.thanhliem.fresh_foods.repository.ProductRepository;
import therookies.thanhliem.fresh_foods.repository.ShopCartRepository;
import therookies.thanhliem.fresh_foods.service.IShopCartService;

import java.util.List;
import java.util.stream.Collectors;

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
        this.mapper.typeMap(ShopCartDTO.class, ShopCartEntity.class).addMapping(ShopCartDTO::getProduct,
                ShopCartEntity::setProduct);
        this.mapper.typeMap(ShopCartDTO.class, ShopCartEntity.class).addMapping(ShopCartDTO::isChecked,
                ShopCartEntity::setChecked);
    }
    @Override
    public ShopCartDTO save(ShopCartDTO dto) {
        // check in save 1 product if is one product increase quantity
        List<ShopCartEntity> list = repository.findByCustomerIdOrderByIdDesc(dto.getCustomerId());
        List<ShopCartEntity> entities = list.stream().filter(p->p.getProduct().getId()==dto.getProduct().getId()).collect(Collectors.toList());
        if(!entities.isEmpty()) {
            dto.setId(entities.get(0).getId());
            dto.setQuantity(entities.get(0).getQuantity()+dto.getQuantity());
        }

        ShopCartEntity entity = mapper.map(dto, ShopCartEntity.class);
        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(()->{throw new IdNotFoundException("Can't not found customer");});
        entity.setCustomer(customer);
        entity = repository.save(entity);
        entity.setProduct(productRepository.getById(dto.getProduct().getId()));
        ShopCartDTO response = mapper.map(entity,ShopCartDTO.class);
        return response;
    }

    @Override
    public List<ShopCartDTO> update(ShopCartDTO dto) {
        ShopCartEntity entity = repository.findById(dto.getId())
                .orElseThrow( () -> {throw new IdNotFoundException("Can't not found item in shop cart");});
        entity.setQuantity(dto.getQuantity());
        entity.setChecked(dto.isChecked());
        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(()->{throw new IdNotFoundException("Can't not found customer");});
        entity.setCustomer(customer);
        ProductEntity product = productRepository.findById(dto.getProduct().getId())
                .orElseThrow(()->{throw new IdNotFoundException("Can't not found product");});
        entity.setProduct(product);
        entity = repository.save(entity);

        List<ShopCartEntity> list = repository.findByCustomerIdOrderByIdDesc(entity.getCustomer().getId());
        return mapper.map(list, new TypeToken<List<ShopCartDTO>>() {
        }.getType());
    }

    @Override
    public List<ShopCartDTO> delete(Long shopcartId) {
        ShopCartEntity entity = repository.findById(shopcartId)
                .orElseThrow(()->{throw new IdNotFoundException("can not found item");});
        repository.delete(entity);
        return getAll(entity.getCustomer().getId());
    }

    @Override
    public List<ShopCartDTO> getAll(Long customerId) {
        List<ShopCartEntity> list = repository.findByCustomerIdOrderByIdDesc(customerId);
        return mapper.map(list, new TypeToken<List<ShopCartDTO>>() {
        }.getType());
    }
}
