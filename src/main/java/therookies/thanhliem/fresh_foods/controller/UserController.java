package therookies.thanhliem.fresh_foods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import therookies.thanhliem.fresh_foods.dto.UserDTO;
import therookies.thanhliem.fresh_foods.service.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    IUserService userService;
    @PostMapping(value = "/user")
    UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }
    @PutMapping(value = "/user/{id}")
    UserDTO update(@RequestBody UserDTO userDTO,@PathVariable("id") Long id) {
        userDTO.setId(id);
        return userService.save(userDTO);
    }
    @GetMapping(value = "/user/{id}")
    UserDTO getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }
}
