package calaru.mapper;

import org.springframework.stereotype.Component;

import calaru.dto.OrdenDeTrabajoDto;
import calaru.model.OrdenDeTrabajo;


@Component
public class OrdenDeTrabajoMapper implements Mapper<OrdenDeTrabajo, OrdenDeTrabajoDto> {

	@Override
	public OrdenDeTrabajoDto entityToDto(OrdenDeTrabajo entity) {
		
		return null;
	}

	@Override
	public OrdenDeTrabajo dtoToEntity(OrdenDeTrabajoDto dto) {
	
		return new OrdenDeTrabajo( dto.getId(),dto.getSector(), dto.getResponsable(), dto.getEquipo(), dto.getFecha(), dto.getOrden(), dto.getTarea(), dto.getTiempo(), dto.getHerramienta());
	}

	
	
}
