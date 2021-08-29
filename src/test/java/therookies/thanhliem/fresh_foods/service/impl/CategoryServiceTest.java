package therookies.thanhliem.fresh_foods.service.impl;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import therookies.thanhliem.fresh_foods.dto.CategoryDTO;
import therookies.thanhliem.fresh_foods.entity.CategoryEntity;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;
import therookies.thanhliem.fresh_foods.exception.CanNotChangeDB;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.CategoryRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTest {
    @Autowired
    CategoryService service;
    @MockBean
    CategoryRepository repository;
    private static final List<CategoryEntity> list = new ArrayList<>();

    @BeforeAll
    static void init() {
        CategoryEntity category1 = new CategoryEntity(1L,"category1");
        CategoryEntity category2 = new CategoryEntity(2L,"category2");
        list.add(category1);
        list.add(category2);
    }


    @Test
    void save() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        CategoryEntity entity = new CategoryEntity();
        entity.setName("Cu");
        entity.setId(1L);
        when(repository.save(entity)).thenReturn(entity);
        //virtual input
        CategoryDTO dto = new CategoryDTO();
        dto.setName("Cu");
        dto.setId(1L);

        assertEquals(dto.getId(),service.save(dto).getId());
    }
    @Test
    void update() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(list.get(0)));
        CategoryEntity entity = new CategoryEntity();
        entity.setName("CuChange");
        entity.setId(1L);
        when(repository.save(entity)).thenReturn(entity);

        //virtual input
        CategoryDTO dto = new CategoryDTO(2L,"CuChange");
        assertEquals(1L,service.save(dto).getId());
        assertEquals("CuChange",service.save(dto).getName());

    }

    @Test
    void findById_Success() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(list.get(0)));
        CategoryDTO dto = new CategoryDTO(1L,"Cu");

        assertEquals(dto.getClass(),service.findById(Mockito.anyLong()).getClass());
    }
    @Test
    void findById_Fail() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(IdNotFoundException.class,()->
                service.findById(Mockito.anyLong()));
        String expectedMessage = "Can not found ";
        String  actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findAll_Success() {
        CategoryEntity category1 = new CategoryEntity();
        when(repository.findAll()).thenReturn(list);

        assertEquals(1L,service.findAll().get(0).getId());
    }

    @Test
    void delete_Success() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(list.get(0)));
        Map<String ,String> respone = new HashMap<>();
        respone.put("Status","Success");

        assertEquals(respone,service.delete(Mockito.anyLong()));
    }
    @Test
    void delete_FailHaveProduct() {
        ProductEntity product = new ProductEntity();
        product.setQuantity(10);
        CategoryEntity entity = list.get(0);
        entity.setProducts(Stream.of(product).collect(Collectors.toList()));
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));

        Exception exception = assertThrows(CanNotChangeDB.class,()->
                service.delete(Mockito.anyLong()));
        String expectedMessage = "Category had product can not delete";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete_FailCanNotFindId() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(IdNotFoundException.class,()->
                service.delete(Mockito.anyLong()));
        String expectedMessage = "Can not found ";
        String  actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}