package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import therookies.thanhliem.fresh_foods.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    @Query(value = "Select id FROM customer where user_id=?1", nativeQuery = true)
    Long checkOnetoOne(Long id);
}
