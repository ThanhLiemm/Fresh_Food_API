package therookies.thanhliem.fresh_foods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import therookies.thanhliem.fresh_foods.dto.PaymentDTO;
import therookies.thanhliem.fresh_foods.service.IPaymentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    IPaymentService paymentService;

    @PostMapping(value = "/payment")
    PaymentDTO create(@Valid  @RequestBody PaymentDTO paymentDTO) {
        if(paymentDTO.getId()==null) paymentDTO.setId(0L);
        return paymentService.save(paymentDTO);
    }

    @PutMapping(value = "/payment/{id}")
    PaymentDTO update(@Valid @RequestBody PaymentDTO paymentDTO, @PathVariable("id") Long id) {
        paymentDTO.setId(id);
        return paymentService.save(paymentDTO);
    }

    @GetMapping(value = "/payment/{id}")
    PaymentDTO getById(@PathVariable("id") Long id) {
        return paymentService.getById(id);
    }

    @GetMapping(value = "/payment")
    List<PaymentDTO> getAll() {
        return paymentService.getAll();
    }

    @DeleteMapping(value = "/payment/{id}")
    Map<String,String> delete(@PathVariable("id") Long id) {
        return paymentService.delete(id);
    }
}
