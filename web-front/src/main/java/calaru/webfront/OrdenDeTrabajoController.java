package calaru.webfront;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import calaru.dto.OrdenDeTrabajoDto;
import calaru.dto.Texto;
import calaru.mapper.Mapper;
import calaru.model.IngresoStock;
import calaru.model.OrdenDeTrabajo;
import calaru.repository.IngresoStockRepository;
import calaru.repository.OrdenDeTrabajoRepository;
import fj.data.Validation;

@RestController
@RequestMapping("/OrdenTrabajo")
public class OrdenDeTrabajoController {
	
	@Autowired
	Mapper<OrdenDeTrabajo, OrdenDeTrabajoDto> ordenTrabajoMapper;
	@Autowired 
	OrdenDeTrabajoRepository repo;
	@Autowired 
	IngresoStockRepository repos;
	
	@RequestMapping()
	public List<OrdenDeTrabajoDto> queryOrdenTrabajo() {
		return ordenTrabajoMapper.entitiesToDtos(repo.findAll());
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public OrdenDeTrabajoDto getOrdenTrabajo(@PathVariable long id) {
		return ordenTrabajoMapper.entityToDto(repo.findOne(id));
	}
	
	
/*	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> postIngreso(@RequestBody OrdenDeTrabajoDto ordenDeTrabajoDto) {
		Validation<String, OrdenDeTrabajo> vm = this.save(ordenTrabajoMapper.dtoToEntity(ordenDeTrabajoDto));
		if(vm.isSuccess()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(
					ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vm.success().getId()).toUri());
			return new ResponseEntity<>(ordenTrabajoMapper.entityToDto(vm.success()), httpHeaders, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Texto>(new Texto(vm.fail()), HttpStatus.CONFLICT);
		}
	}
	*/
	private Validation<String, IngresoStock> save(IngresoStock ordenDeTrabajo) {
		return Validation.success(repos.save(ordenDeTrabajo));
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> putParamPrefondeo(@PathVariable long id, @RequestBody IngresoStock ingresoStock) {
		
		IngresoStock entity = repos.findOne(id);	
		int total = entity.getCantidadMaxima() - ingresoStock.getCantidad();
		entity.setCantidadMaxima(total);
		
		Validation<String, IngresoStock> vppf = save(entity);
		if(vppf.isSuccess()) {
			return new ResponseEntity<>(ingresoStock, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Texto>(new Texto(vppf.fail()), HttpStatus.CONFLICT);
		}
	}

}
