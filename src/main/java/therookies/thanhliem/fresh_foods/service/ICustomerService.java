package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.CustomerDTO;
import therookies.thanhliem.fresh_foods.entity.CustomerEntity;

public interface ICustomerService {
    CustomerDTO update(CustomerDTO customerDTO);
    CustomerDTO getById(Long id);
}
