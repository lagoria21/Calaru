package calaru.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import calaru.dto.CantidadStockDto;
import calaru.dto.EmailDto;
import calaru.mapper.Mapper;
import calaru.model.IngresoStock;
import calaru.model.Users;
import calaru.repository.IngresoStockRepository;
import calaru.repository.UsersRepository;


@RestController
public class ComunesController {
	
	@Autowired
	private Mapper<IngresoStock, CantidadStockDto> stockMapper;
	@Autowired
	private IngresoStockRepository stockCantidad;
	
	@Autowired
	private Mapper<Users, EmailDto> emailMapper;
	
	@Autowired
	private UsersRepository userRepository;
	
	
	
	@RequestMapping(value="/cantidadStock", method = RequestMethod.GET)
	public List<CantidadStockDto> queryCantidad()
	{
		List<CantidadStockDto> lista = stockMapper.entitiesToDtos(stockCantidad.findAll());
		return lista;
	}
	
	@RequestMapping(value="/listaDeEmail", method = RequestMethod.GET)
	public List<EmailDto> queryCantidadDeEmail()
	{
		List<EmailDto> lista = emailMapper.entitiesToDtos(userRepository.findAll());
		return lista;
	}
	
}
