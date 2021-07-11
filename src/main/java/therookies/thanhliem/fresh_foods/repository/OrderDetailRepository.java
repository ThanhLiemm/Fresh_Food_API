package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import therookies.thanhliem.fresh_foods.entity.OrderDetailEntity;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {
    @Query(value = "select * from orderitem where shopcart_id=?1",nativeQuery = true)
    List<OrderDetailEntity> getAllByShopCart(Long id);
    @Query(value = "select id from orderitem where product_id=?1 and shopcart_id=?2",nativeQuery = true)
    Long existsProduct(Long id, Long shopcartId);
}
