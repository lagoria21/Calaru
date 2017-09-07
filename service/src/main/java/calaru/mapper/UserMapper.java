package calaru.mapper;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import calaru.dto.UserDto;
import calaru.model.Role;
import calaru.model.User;
import calaru.repository.RoleRepository;

@Component
public class UserMapper implements Mapper<User, UserDto> {
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	@Autowired
    private RoleRepository roleRepository;
	
	
	@Override
	public UserDto entityToDto(User i) {	
		
		UserDto dto = new UserDto();
		dto.setId(i.getId());
		dto.setEmail(i.getEmail());
		dto.setName(i.getName());
		dto.setLastName(i.getLastName());
		dto.setActive(i.getActive());
		
		return dto;
	}
	
	@Override
	public User dtoToEntity(UserDto dto) {
		
			User entity = new User();
			entity.setEmail(dto.getEmail());
			entity.setPassword(passwordEncoder.encode(dto.getPassword()));
			entity.setName(dto.getName());
			entity.setLastName(dto.getLastName());
			entity.setActive(dto.getActive());
			Role userRole = roleRepository.findByRole("USER");
			entity.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			return entity;
	}
	
	

}
