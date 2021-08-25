package therookies.thanhliem.fresh_foods.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity ,Long> {
    @Query(value = " SELECT * from product p where p.id = ?1",nativeQuery = true)
    ProductEntity findOneById(Long id);

    @Query(value = "SELECT * from product where category_id =?1",nativeQuery = true)
    List<ProductEntity> getAllByCategoryId(Long id);

    @Query(value = "SELECT * from product where deadline>=CURRENT_DATE  order by discount desc, rating desc limit 10",nativeQuery = true)
    List<ProductEntity> getTopDiscount();

    Page<ProductEntity> findByCategory_Id(Long id, Pageable pageable);

    @Query(value = "SELECT count(id) from product where status= 'ACTIVE' ",nativeQuery = true)
    Integer totalProduct();

    @Query(value = "SELECT count(id) from product where category_id =?1 and status= 'ACTIVE' ",nativeQuery = true)
    Integer totalProductCategory(Long id);
    
    @Query(value = "SELECT * from product where status= 'ACTIVE' ",nativeQuery = true)
	Page<ProductEntity> findAllCustomer(Pageable pageable);
    
    @Query(value = "SELECT * from product where status= 'ACTIVE' and category_id = ?1",nativeQuery = true)
    Page<ProductEntity> findByCategory_IdCutomer(Long id, Pageable pageable);

}
