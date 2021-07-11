package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    OrderDTO save (OrderDTO orderDTO);
    OrderDTO update (OrderDTO orderDTO);
    OrderDTO getById(Long id, Long customerId);
    OrderDTO getById(Long id);
    List<OrderDTO> getAll();
    List<OrderDTO> getAllByCustomer(Long id);
}
