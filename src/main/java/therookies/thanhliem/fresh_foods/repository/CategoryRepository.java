package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import therookies.thanhliem.fresh_foods.entity.CategoryEntity;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    @Query(value = "Select * From category c where c.id =?1",nativeQuery = true)
    CategoryEntity findOneById(Long id);
}
