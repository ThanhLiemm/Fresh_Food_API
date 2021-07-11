package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.OrderDetailDTO;

import java.util.List;
import java.util.Map;

public interface IOrderDetailService {
    OrderDetailDTO save(OrderDetailDTO orderDetailDTO);
    OrderDetailDTO getById(Long id, Long shopcartId);
    Map<String,String> delete(Long id,Long shopcartId);
    OrderDetailDTO update(OrderDetailDTO orderDetailDTO);
    List<OrderDetailDTO> getAllByShopCart(Long id);
}
