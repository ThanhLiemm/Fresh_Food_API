package therookies.thanhliem.fresh_foods.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import therookies.thanhliem.fresh_foods.dto.CustomerDTO;
import therookies.thanhliem.fresh_foods.entity.CustomerEntity;
import therookies.thanhliem.fresh_foods.entity.UserEntity;
import therookies.thanhliem.fresh_foods.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService service;
    @MockBean
    CustomerRepository repository;
    static private final CustomerEntity customer = new CustomerEntity();
    @Autowired
    ModelMapper mapper;

    @BeforeAll
    private static void init() {
        customer.setId(1L);
        customer.setPhone("0141084");
        customer.setLastname("Nguyen");
        customer.setFirstname("Thanh Liem");
        customer.setAddress("/151A");
        customer.setUser(new UserEntity());
    }
    @Test
    void update() {
        when(repository.getById(Mockito.any())).thenReturn(customer);
        when(repository.save(Mockito.any(CustomerEntity.class))).thenReturn(customer);
        CustomerDTO dto = mapper.map(customer,CustomerDTO.class);

        assertEquals(dto.getId(),service.update(dto).getId());
    }


    @Test
    void getById() {
        when(repository.getById(Mockito.anyLong())).thenReturn(customer);

        assertEquals(1L,service.getById(Mockito.anyLong()).getId());
    }
}