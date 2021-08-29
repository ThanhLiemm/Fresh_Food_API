package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import therookies.thanhliem.fresh_foods.entity.ShopCartEntity;
import therookies.thanhliem.fresh_foods.entity.ShopCartEntity.ShopCartId;
import java.util.List;

public interface ShopCartRepository extends JpaRepository<ShopCartEntity, ShopCartId> {
    List<ShopCartEntity> findByCustomerIdOrderByIdDesc(Long id);
}
