package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.ShopCartDTO;
import therookies.thanhliem.fresh_foods.entity.ShopCartEntity;
import therookies.thanhliem.fresh_foods.entity.ShopCartEntity.ShopCartId;

import java.util.List;

public interface IShopCartService {
    ShopCartDTO save(ShopCartDTO dto);
    List<ShopCartDTO> update(ShopCartDTO dto);
    List<ShopCartDTO> delete(Long customerId, Long productId);
    List<ShopCartDTO> getAll(Long customerId);
}
