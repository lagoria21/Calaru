package calaru.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import calaru.dto.ConsultaDto;
import calaru.model.IngresoStock;

@Component
public class ConsultaMapper implements Mapper<IngresoStock, ConsultaDto> {

	@Override
	public ConsultaDto entityToDto(IngresoStock entity) {
		ConsultaDto dto = new ConsultaDto();
		String nuevoCodigo = String.valueOf(entity.getId());
		dto.setCodigo(nuevoCodigo);		
		dto.setDescripcion(entity.getDescripcion());
		dto.setPrecioUnitario(String.valueOf(entity.getPrecioUnitario()));		
		BigDecimal subTotal = entity.getPrecioUnitario().multiply(new BigDecimal(entity.getCantidadMaxima()));
		dto.setPrecioTotal(String.valueOf(subTotal));		
		dto.setStockMinimo(String.valueOf(entity.getCantidadMinima()));
		dto.setStockActual(String.valueOf(entity.getCantidadMaxima()));
		return dto;
	}

	@Override
	public IngresoStock dtoToEntity(ConsultaDto dto) {

		return null;
	}

}
