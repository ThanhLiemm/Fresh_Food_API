package therookies.thanhliem.fresh_foods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import therookies.thanhliem.fresh_foods.dto.ShopCartDTO;
import therookies.thanhliem.fresh_foods.entity.UserEntity;
import therookies.thanhliem.fresh_foods.repository.UserRepository;
import therookies.thanhliem.fresh_foods.service.IShopCartService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ShopCartController {
    @Autowired
    IShopCartService service;
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

    @GetMapping(value = "/shopcart")
    public List<ShopCartDTO> getAll() {
        setUser();
        return service.getAll(user.get().getCustomer().getId());
    }

    @GetMapping(value = "/shopcart/count")
    public Map<String,Integer> count() {
        setUser();
        int count = service.getAll(user.get().getCustomer().getId()).size();
        Map<String,Integer> response = new HashMap<>();
        response.put("Count",count);
        return response;
    }
    @PostMapping(value = "/shopcart")
    public ShopCartDTO save (@RequestBody ShopCartDTO dto) {
        setUser();
        dto.setCustomerId(user.get().getCustomer().getId());
        return service.save(dto);
    }
    @PutMapping(value = "/shopcart")
    public List<ShopCartDTO> update(@RequestBody ShopCartDTO dto) {
        setUser();
        dto.setCustomerId(user.get().getCustomer().getId());
        return service.update(dto);
    }
    @DeleteMapping(value = "/shopcart")
    public List<ShopCartDTO> delete(@RequestParam(name = "productId") Long productid) {
        Long customerId = user.get().getCustomer().getId();
        return service.delete(customerId,productid);
    }
}
