package therookies.thanhliem.fresh_foods.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import therookies.thanhliem.fresh_foods.dto.ImageDTO;
import therookies.thanhliem.fresh_foods.dto.ProductDTO;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.CategoryRepository;
import therookies.thanhliem.fresh_foods.repository.ProductRepository;
import  therookies.thanhliem.fresh_foods.entity.ProductEntity.Status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService service;

    @MockBean
    ProductRepository repository;

    @MockBean
    CategoryRepository caRepository;

    private static List<ProductEntity> listProduct = new ArrayList<>();

    @Autowired
    ModelMapper mapper;

    @BeforeAll
    public static void init() {
        ProductEntity product1 = new ProductEntity(1L,"Bap Cai","Bap cai rat la ngon",10000f,20,140, Status.ACTIVE,
                new Date(),"kg",100,null,null,null);
        ProductEntity product2 = new ProductEntity(2L,"Bap Chuoi","Bap chuoi rat la ngon",10000f,20,140, Status.ACTIVE,
                new Date(),"kg",100,null,null,null);
        listProduct.add(product1);
        listProduct.add(product2);
    }


    @Test
    void save_Success() {
        Mockito.when(caRepository.existsById(Mockito.anyObject())).thenReturn(true);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any(ProductEntity.class))).thenReturn(listProduct.get(0));

        ProductDTO dto = mapper.map(listProduct.get(0),ProductDTO.class);
        dto.setListImage(Stream.of(new ImageDTO(1L,"http://abc.com",null),
                new ImageDTO(2L,"http://bac.com",null))
                .collect(Collectors.toList()));

        assertEquals(dto.getId(),service.save(dto).getId());
        assertEquals(dto.getName(),service.save(dto).getName());
    }
    @Test
    void save_Fail() {
        Mockito.when(caRepository.existsById(Mockito.anyObject())).thenReturn(false);
        ProductDTO dto = mapper.map(listProduct.get(0),ProductDTO.class);
        Exception exception = assertThrows(IdNotFoundException.class,()->
                service.save(dto));
        String expectedMessage = "Can not found ";
        String  actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void update() {
        Mockito.when(caRepository.existsById(Mockito.anyObject())).thenReturn(true);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(listProduct.get(0)));
        Mockito.when(repository.save(Mockito.any(ProductEntity.class))).thenReturn(listProduct.get(0));
        ProductDTO dto = mapper.map(listProduct.get(0),ProductDTO.class);

        assertEquals(dto.getId(),service.save(dto).getId());
        assertEquals(dto.getName(),service.save(dto).getName());
    }

    @Test
    void findById_Success() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(listProduct.get(0)));
        ProductDTO dto = mapper.map(listProduct.get(0),ProductDTO.class);

        assertEquals(dto.getId(),service.findById(Mockito.anyLong()).getId());
        assertEquals(dto.getName(),service.findById(Mockito.anyLong()).getName());
    }

    @Test
    void findById_Fail() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        ProductDTO dto = mapper.map(listProduct.get(0),ProductDTO.class);
        Exception exception = assertThrows(IdNotFoundException.class,()->
                service.findById(Mockito.anyLong()));
        String expectedMessage = "Can not found ";
        String  actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(listProduct);
        assertEquals(2,service.findAll().size());
    }

    @Test
    void delete_Success() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(listProduct.get(0)));
        Map<String,String> respone = new HashMap<>();
        respone.put("Status","Success");

        assertEquals(respone,service.delete(Mockito.anyLong()));
    }

    @Test
    void delete_Fail() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(IdNotFoundException.class,()->
                service.findById(Mockito.anyLong()));
        String expectedMessage = "Can not found ";
        String  actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findByCategoryId_Success() {
        Mockito.when(caRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repository.getAllByCategoryId(Mockito.anyLong())).thenReturn(listProduct);

        assertEquals(2,service.findByCategoryId(Mockito.anyLong()).size());
    }
    @Test
    void findByCategoryId_Fail() {
        Mockito.when(caRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Exception exception = assertThrows(IdNotFoundException.class,()->
                service.findByCategoryId(Mockito.anyLong()));
        String expectedMessage = "Can not found ";
        String  actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}