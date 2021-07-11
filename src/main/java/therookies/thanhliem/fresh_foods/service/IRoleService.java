package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.RoleDTO;

import java.util.List;
import java.util.Map;

public interface IRoleService {
    RoleDTO save(RoleDTO roleDTO);
    RoleDTO getById(Long id);
    List<RoleDTO> getAll();
    Map<String ,String> delete(Long id);
}
