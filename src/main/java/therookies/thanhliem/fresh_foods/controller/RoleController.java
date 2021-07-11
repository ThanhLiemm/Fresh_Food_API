package therookies.thanhliem.fresh_foods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import therookies.thanhliem.fresh_foods.dto.RoleDTO;
import therookies.thanhliem.fresh_foods.service.impl.RoleService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping(value = "/role")
    RoleDTO create(@RequestBody RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }
    @PutMapping(value="/role/{id}")
    RoleDTO update(@RequestBody RoleDTO roleDTO,@PathVariable("id") Long id) {
        roleDTO.setId(id);
        return roleService.save(roleDTO);
    }
    @GetMapping(value="/role/{id}")
    RoleDTO getById(@PathVariable("id") Long id) {
        return roleService.getById(id);
    }
    @GetMapping(value="/role")
    List<RoleDTO> getAll() {
        return roleService.getAll();
    }
    @DeleteMapping(value = "role/{id}")
    Map<String,String> delete( @PathVariable("id") Long id) {
        return roleService.delete(id);
    }

}
