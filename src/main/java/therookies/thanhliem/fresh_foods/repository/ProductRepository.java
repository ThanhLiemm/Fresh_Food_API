package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity ,Long> {
    @Query(value = " Select * from product p where p.id = ?1",nativeQuery = true)
    ProductEntity findOneById(Long id);

    @Query(value = "select * from product where category_id =?1",nativeQuery = true)
    List<ProductEntity> getAllByCategoryId(Long id);

}
