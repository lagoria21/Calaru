package calaru.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import calaru.dto.CantidadStockDto;
import calaru.mapper.Mapper;
import calaru.model.IngresoStock;
import calaru.repository.IngresoStockRepository;


@RestController
public class ComunesController {
	
	@Autowired
	private Mapper<IngresoStock, CantidadStockDto> stockMapper;
	@Autowired
	private IngresoStockRepository stockCantidad;
	
	
	@RequestMapping(value="/cantidadStock", method = RequestMethod.GET)
	public List<CantidadStockDto> queryCantidad()
	{
		List<CantidadStockDto> lista = stockMapper.entitiesToDtos(stockCantidad.findAll());
		return lista;
	}
}
