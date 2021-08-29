package therookies.thanhliem.fresh_foods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import therookies.thanhliem.fresh_foods.dto.OrderDTO;
import therookies.thanhliem.fresh_foods.entity.RoleName;
import therookies.thanhliem.fresh_foods.entity.UserEntity;
import therookies.thanhliem.fresh_foods.repository.UserRepository;
import therookies.thanhliem.fresh_foods.service.IOrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    IOrderService orderService;
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

    @PostMapping(value = "/order")
    public OrderDTO create(@Valid @RequestBody OrderDTO orderDTO) {
        setUser();
        orderDTO.setCustomerId(user.get().getId());
        return orderService.save(orderDTO);
    }

    @PutMapping(value="/order/{id}")
    public OrderDTO update(@Valid  @RequestBody OrderDTO orderDTO, @PathVariable(name = "id") Long id) {
        orderDTO.setId(id);
        return orderService.update(orderDTO);
    }
    @GetMapping(value="/order")
    public List<OrderDTO> getAll() {
        setUser();
       Long check = user.get().getRoleEntities().stream().filter(role-> role.getName()==RoleName.ROLE_ADMIN).count();
        if(check>0) return orderService.getAll();
        return orderService.getAllByCustomer(user.get().getId());
    }

    @GetMapping(value="/order/{id}")
    public OrderDTO getOneInCustomerId(@PathVariable(name = "id") Long id) {
        setUser();
        Long check = user.get().getRoleEntities().stream().filter(role-> role.getName()==RoleName.ROLE_ADMIN).count();
        if(check>0) return orderService.getById(id);
        return orderService.getById(id,user.get().getId());
    }
}
