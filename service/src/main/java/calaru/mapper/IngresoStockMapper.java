package calaru.mapper;

import org.springframework.stereotype.Component;

import calaru.dto.IngresoStockDto;
import calaru.model.IngresoStock;


@Component
public class IngresoStockMapper implements Mapper<IngresoStock, IngresoStockDto> {

	
	@Override
	public IngresoStockDto entityToDto(IngresoStock i) {
		return new IngresoStockDto(i.getId(),i.getDescripcion(),i.getCantidad(),i.getFechaIngreso(),i.getProveedor(),i.getCuit(),i.getSector(),i.getUbicacion(),i.getCodigo(), i.getCantidadMinima(), i.getCantidadMaxima(), i.getPrecioUnitario());
	}

	@Override
	public IngresoStock dtoToEntity(IngresoStockDto dto) {
		return new IngresoStock(dto.getId(),dto.getDescripcion(),dto.getCantidad(),dto.getFechaIngreso(),dto.getProveedor(),dto.getCuit(),dto.getSector(),dto.getUbicacion(),dto.getCodigo(), dto.getCantidadMinima(), dto.getCantidadMaxima(), dto.getPrecioUnitario(),null);
	}


	
}
