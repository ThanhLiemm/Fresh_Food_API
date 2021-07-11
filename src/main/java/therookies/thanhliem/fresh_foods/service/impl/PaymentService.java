package therookies.thanhliem.fresh_foods.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import therookies.thanhliem.fresh_foods.dto.PaymentDTO;
import therookies.thanhliem.fresh_foods.entity.PaymentEntity;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.PaymentRepository;
import therookies.thanhliem.fresh_foods.service.IPaymentService;



@Service
public class PaymentService implements IPaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public PaymentDTO save(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentDTO.getId())
                    .map(payment ->{
                        payment.setName(paymentDTO.getName());
                        payment.setUrl(paymentDTO.getUrl());
                        return payment;
                    })
                    .orElseGet(()->{
                        return mapper.map(paymentDTO,PaymentEntity.class);
                    });
        paymentEntity = paymentRepository.save(paymentEntity);
        return mapper.map(paymentEntity,PaymentDTO.class);
    }

    @Override
    public PaymentDTO getById(Long id) {
        PaymentEntity paymentEntity = paymentRepository.findById(id)
                .orElseThrow(()->{throw new IdNotFoundException("Can not found payment id = "+id);});
        return mapper.map(paymentEntity,PaymentDTO.class);
    }

    @Override
    public List<PaymentDTO> getAll() {
        List<PaymentEntity> paymentEntities = paymentRepository.findAll();
        return mapper.map(paymentEntities, new TypeToken<List<PaymentDTO>>() {}.getType());
    }

    @Override
    public Map<String, String> delete(Long id) {
        if(!paymentRepository.existsById(id)) throw new IdNotFoundException("Can not found payment id = "+id);
        paymentRepository.deleteById(id);
        Map<String,String> reponse = new HashMap<>();
        reponse.put("Status","Success");
        return reponse;
    }
}
