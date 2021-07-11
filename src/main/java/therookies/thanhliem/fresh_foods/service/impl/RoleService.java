package therookies.thanhliem.fresh_foods.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import therookies.thanhliem.fresh_foods.dto.RoleDTO;
import therookies.thanhliem.fresh_foods.entity.RoleEntity;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.RoleRepository;
import therookies.thanhliem.fresh_foods.service.IRoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper mapper;
    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        RoleEntity roleEntity;
        if(roleDTO.getId()==null) roleEntity = mapper.map(roleDTO,RoleEntity.class);
        else {
            roleEntity = roleRepository.findById(roleDTO.getId())
            .map(role -> {
                return role;
            })
            .orElseGet(()->{
                return mapper.map(roleDTO,RoleEntity.class);
            });
        }
        roleEntity = roleRepository.save(roleEntity);
        return mapper.map(roleEntity,RoleDTO.class);
    }

    @Override
    public RoleDTO getById(Long id) {
        if(! roleRepository.existsById(id)) throw new IdNotFoundException("Can not found role id = "+id);
        RoleEntity roleEntity = roleRepository.getById(id);
        return mapper.map(roleEntity,RoleDTO.class);
    }

    @Override
    public List<RoleDTO> getAll() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return mapper.map(roleEntities, new TypeToken<List<RoleDTO>>() {}.getType());
    }

    @Override
    public Map<String, String> delete(Long id) {
        if(! roleRepository.existsById(id)) throw new IdNotFoundException("Can not found role id = "+id);
        //if(roleRepository.numberUser(id)!=0) throw  new CanNotDeleteItem("Can not delete role id = "+id);
        roleRepository.deleteById(id);
        Map<String,String> respone = new HashMap<>();
        respone.put("Status","Success");
        return respone;
    }
}
