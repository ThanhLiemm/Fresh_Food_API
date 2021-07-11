package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import therookies.thanhliem.fresh_foods.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
}
