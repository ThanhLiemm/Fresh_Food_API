package therookies.thanhliem.fresh_foods.service;

import therookies.thanhliem.fresh_foods.dto.UserDTO;

import java.util.Map;

public interface IUserService {
    UserDTO save(UserDTO userDTO);
    UserDTO getById (Long id);
    Map<String,String> deleteUser(Long id);

}
