package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import therookies.thanhliem.fresh_foods.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    @Query(value = "Select id,name,status,parentId,createdby,createddate,modifiedby,modifieddate From category c where c.id =?1",nativeQuery = true)
    CategoryEntity findOneById(Long id);
    @Query(value = "Select count(id) FROM product where category_id=?1", nativeQuery = true)
    int numberProduct(Long id);
    
}
