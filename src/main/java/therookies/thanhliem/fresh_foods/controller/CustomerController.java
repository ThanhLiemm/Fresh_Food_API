package therookies.thanhliem.fresh_foods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import therookies.thanhliem.fresh_foods.dto.CustomerDTO;
import therookies.thanhliem.fresh_foods.entity.UserEntity;
import therookies.thanhliem.fresh_foods.repository.UserRepository;
import therookies.thanhliem.fresh_foods.service.ICustomerService;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    ICustomerService customerService;
    @Autowired
    UserRepository userRepository;
    private Optional<UserEntity> user;
    private void setUser() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        this.user = userRepository.findByUsername(username);
    }
    @PutMapping(value = "/customer")
    CustomerDTO update (@Valid  @RequestBody CustomerDTO customerDTO) {
        setUser();
        customerDTO.setUserId(user.get().getId());
        return customerService.update(customerDTO);
    }
    @GetMapping(value = "/customer")
    CustomerDTO getById() {
        setUser();
        return customerService.getById(user.get().getId());
    }
}
