package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import therookies.thanhliem.fresh_foods.entity.OrderDetailEntity;
import therookies.thanhliem.fresh_foods.entity.OrderDetailEntity.OrderDetailId;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, OrderDetailId> {

}
