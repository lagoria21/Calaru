package calaru.mapper;

import org.springframework.stereotype.Component;

import calaru.dto.CantidadStockDto;
import calaru.model.IngresoStock;


@Component
public class CantidadStockMapper implements Mapper<IngresoStock, CantidadStockDto> {

   @Override
   public CantidadStockDto entityToDto(IngresoStock i) {
	return new CantidadStockDto(i.getId(), i.getDescripcion(), i.getCantidad(), i.getCodigo(), i.getCantidadMaxima());
	}

	@Override
	public IngresoStock dtoToEntity(CantidadStockDto dto) {
	return null;
	}

}

	

