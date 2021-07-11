package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.PaymentDTO;
import therookies.thanhliem.fresh_foods.entity.PaymentEntity;

import java.util.List;
import java.util.Map;

public interface IPaymentService {
    PaymentDTO save(PaymentDTO paymentDTO);
    PaymentDTO getById(Long id);
    List<PaymentDTO> getAll();
    Map<String,String> delete(Long id);
}
