package calaru.mapper;

import org.springframework.stereotype.Component;

import calaru.dto.EmailDto;
import calaru.model.Users;

@Component
public class EmailMapper implements Mapper<Users, EmailDto> {

	@Override
	public EmailDto entityToDto(Users entity) {
		
		EmailDto dto = new EmailDto();
		dto.setEmail(entity.getEmail());
		
		return dto;
	}

	@Override
	public Users dtoToEntity(EmailDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
