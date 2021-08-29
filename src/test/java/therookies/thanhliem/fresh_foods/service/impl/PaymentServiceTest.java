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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import therookies.thanhliem.fresh_foods.dto.PaymentDTO;
import therookies.thanhliem.fresh_foods.entity.OrderEntity;
import therookies.thanhliem.fresh_foods.entity.PaymentEntity;
import therookies.thanhliem.fresh_foods.exception.CanNotChangeDB;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.PaymentRepository;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    PaymentService service;

    @Autowired
    ModelMapper mapper;

    @MockBean
    PaymentRepository repository;

    private static final List<PaymentEntity> listPayment = new ArrayList<>();

    @BeforeAll
    public static void init() {
        PaymentEntity payment1 = new PaymentEntity(1L,"Thanh toan truc tuyen", "",new ArrayList<>());
        PaymentEntity payment2 = new PaymentEntity(2L,"Thanh toan tk ngan hang", "",
                Stream.of(new OrderEntity()).collect(Collectors.toList()));
        listPayment.add(payment1);
        listPayment.add(payment2);
    }
    @Test
    void save() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        when(repository.save(Mockito.anyObject())).thenReturn(listPayment.get(0));
        PaymentDTO dto = mapper.map(listPayment.get(0),PaymentDTO.class);

        assertEquals(dto.getName(),service.save(dto).getName());
        assertEquals(dto.getId(),service.save(dto).getId());
    }

    @Test
    void Update() {

        PaymentEntity paymentOld = new PaymentEntity(1L,"Thanh Toan MoMo", "",null);
        PaymentEntity paymentNew = new PaymentEntity(100L,"Thanh Toan Bang The", "",null);
        PaymentEntity paymentUpdate = new PaymentEntity(1L,"Thanh Toan Bang The","",null);
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(paymentOld));
        when(repository.save(paymentUpdate)).thenReturn(paymentUpdate);

        PaymentDTO dto = mapper.map(paymentNew,PaymentDTO.class);

        assertEquals(1L,service.save(dto).getId()); //old
        assertEquals("Thanh Toan Bang The",service.save(dto).getName());
    }

    @Test
    void getById_Success() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(listPayment.get(0)));

        assertEquals(1L,service.getById(Mockito.anyLong()).getId());
        assertEquals("Thanh toan truc tuyen",service.getById(Mockito.anyLong()).getName());
    }

    @Test
    void getById_Fail() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(IdNotFoundException.class,()->
                service.getById(Mockito.anyLong()));
        String expectedMessage = "Can not found ";
        String  actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(listPayment);

        assertEquals(2,service.getAll().size());
    }

    @Test
    void delete_Success() {
        when(repository.existsById(Mockito.anyLong())).thenReturn(true);
        when(repository.getById(Mockito.anyLong())).thenReturn(listPayment.get(0));
        Map<String,String> respone = new HashMap<>();
        respone.put("Status","Success");

        assertEquals(respone,service.delete(Mockito.anyLong()));
    }
    @Test
    void delete_Fail() {
        when(repository.existsById(Mockito.anyLong())).thenReturn(false);
        Exception exception = assertThrows(IdNotFoundException.class,()->
                service.delete(Mockito.anyLong()));
        String expectedMessage = "Can not found ";
        String  actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete_HaveOrder() {
        when(repository.existsById(Mockito.anyLong())).thenReturn(true);
        when(repository.getById(Mockito.anyLong())).thenReturn(listPayment.get(1));

        Exception exception = assertThrows(CanNotChangeDB.class,()->
                service.delete(1L));
        String expectedMessage = "Payment had order";
        String  actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}