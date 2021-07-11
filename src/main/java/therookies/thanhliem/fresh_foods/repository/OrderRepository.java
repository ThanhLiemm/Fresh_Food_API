package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import therookies.thanhliem.fresh_foods.entity.OrderEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query(value = "select * from orders where customer_id=?1",nativeQuery = true)
    List<OrderEntity> getAllByCustomer(Long id);
    @Query(value = "select * from orders where id=?1 and customer_id=?2",nativeQuery = true)
    OrderEntity getByCustomnerId(Long id,Long customerId);

}
