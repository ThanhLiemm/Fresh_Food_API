package therookies.thanhliem.fresh_foods.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import therookies.thanhliem.fresh_foods.dto.ProductDTO;
import therookies.thanhliem.fresh_foods.dto.UserDTO;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;
import therookies.thanhliem.fresh_foods.entity.UserEntity;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.RoleRepository;
import therookies.thanhliem.fresh_foods.repository.UserRepository;
import therookies.thanhliem.fresh_foods.service.IUserService;

import java.util.Map;
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper mapper;

    public UserService(ModelMapper modelMapper) {
        this.mapper = modelMapper;
        this.mapper.typeMap(UserEntity.class, UserDTO.class).addMapping(UserEntity::getCustomer,UserDTO::setCustomerDTO);
    }
    @Override
    public UserDTO save(UserDTO userDTO) {
        if(!roleRepository.existsById(userDTO.getRoleId())) throw new IdNotFoundException("Can not found role id = "+userDTO.getRoleId());

        UserEntity userEntity;
        if(userDTO.getId()==null) userEntity = mapper.map(userDTO,UserEntity.class);
        else {
            userEntity = userRepository.findById(userDTO.getId())
                    .map(user ->{
                        user.setUsername(userDTO.getUsername());
                        user.setPassword(userDTO.getPassword());
                        return user;
                    })
                    .orElseGet(()->{
                        return mapper.map(userDTO, UserEntity.class);
                    });
        }
        userEntity = userRepository.save(userEntity);
        return mapper.map(userEntity,UserDTO.class);
    }

    @Override
    public UserDTO getById(Long id) {
        if(!userRepository.existsById(id)) throw new IdNotFoundException("Can not found user id = "+id);
        UserEntity userEntity = userRepository.getById(id);
        return mapper.map(userEntity,UserDTO.class);
    }

    @Override
    public Map<String, String> deleteUser(Long id) {
        return null;
    }
}
