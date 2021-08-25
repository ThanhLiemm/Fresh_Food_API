package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.ShopCartDTO;

import java.util.List;

public interface IShopCartService {
    ShopCartDTO save(ShopCartDTO dto);
    List<ShopCartDTO> update(ShopCartDTO dto);
    List<ShopCartDTO> delete(Long shopcartId);
    List<ShopCartDTO> getAll(Long customerId);
}
