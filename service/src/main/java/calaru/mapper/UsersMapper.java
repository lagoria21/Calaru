package calaru.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import calaru.dto.UserDto;
import calaru.model.Users;
import calaru.repository.RoleRepository;

@Component
public class UsersMapper implements Mapper<Users, UserDto>{
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	@Autowired
    private RoleRepository roleRepository;
	
	
	@Override
	public UserDto entityToDto(Users i) {	
		
		UserDto dto = new UserDto();
		dto.setId(i.getId());
		dto.setEmail(i.getEmail());
		dto.setName(i.getName());
		dto.setLastName(i.getLastName());
		dto.setActive(i.getActive());
		
		return dto;
	}
	
	@Override
	public Users dtoToEntity(UserDto i) {
		
		Users entity = new Users();
		entity.setId(i.getId());
		entity.setEmail(i.getEmail());
		entity.setName(i.getName());
		entity.setLastName(i.getLastName());
		entity.setActive(i.getActive());

		return entity;
	}

}
