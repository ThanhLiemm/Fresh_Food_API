package therookies.thanhliem.fresh_foods.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import therookies.thanhliem.fresh_foods.dto.CustomerDTO;
import therookies.thanhliem.fresh_foods.entity.CustomerEntity;
import therookies.thanhliem.fresh_foods.repository.CustomerRepository;
import therookies.thanhliem.fresh_foods.repository.UserRepository;
import therookies.thanhliem.fresh_foods.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper mapper;
    public CustomerService(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }
    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerRepository.getById(customerDTO.getUserId());
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setFirstname(customerDTO.getFirstname());
        customerEntity.setLastname(customerDTO.getLastname());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity = customerRepository.save(customerEntity);
        return mapper.map(customerEntity,CustomerDTO.class);
    }

    @Override
    public CustomerDTO getById(Long id) {
        CustomerEntity customerEntity = customerRepository.getById(id);
        return mapper.map(customerEntity,CustomerDTO.class);
    }
}
