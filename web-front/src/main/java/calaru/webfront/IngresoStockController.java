package calaru.webfront;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import calaru.dto.IngresoStockDto;
import calaru.dto.Texto;
import calaru.mail.MailService;
import calaru.mapper.Mapper;
import calaru.model.IngresoStock;
import calaru.model.IngresoStock_;
import calaru.repository.IngresoStockRepository;
import calaru.util.pager.Paginator;
import calaru.util.pager.SeleccionDto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fj.data.Validation;

@RestController
@RequestMapping("/stock")
public class IngresoStockController {
	
	@Autowired
	Mapper<IngresoStock, IngresoStockDto> ingresoStockMapper;
	@Autowired 
	IngresoStockRepository repo;
	@Autowired
	Paginator paginador;	
	@Autowired
	MailService mailService;
	
	@RequestMapping()
	public List<IngresoStockDto> queryIngresoStock() {
		return ingresoStockMapper.entitiesToDtos(repo.findAll());
	}
	
	
//	@RequestMapping()
//	public void queryIngresoStock() throws AddressException, MessagingException {
//		mailService.enviar();
//	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public IngresoStockDto getIngreso(@PathVariable long id) {
		return ingresoStockMapper.entityToDto(repo.findOne(id));
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> postIngreso(@RequestBody IngresoStockDto ingresoStockDto) {
		Validation<String, IngresoStock> vm = this.save(ingresoStockMapper.dtoToEntity(ingresoStockDto));
		if(vm.isSuccess()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(
					ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vm.success().getId()).toUri());
			return new ResponseEntity<>(ingresoStockMapper.entityToDto(vm.success()), httpHeaders, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Texto>(new Texto(vm.fail()), HttpStatus.CONFLICT);
		}
	}
	
		
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> putParamPrefondeo(@PathVariable long id, @RequestBody IngresoStock ingresoStock) {
		Validation<String, IngresoStock> vppf = save(ingresoStock);
		if(vppf.isSuccess()) {
			return new ResponseEntity<>(ingresoStock, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Texto>(new Texto(vppf.fail()), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/agregar/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> putAgregar(@PathVariable long id, @RequestBody IngresoStock ingresoStock) {
		
		int e = Integer.parseInt(ingresoStock.getIngresoProducto());
		int suma = e + ingresoStock.getCantidadMaxima();
		
		ingresoStock.setCantidadMaxima(suma);
		
		Validation<String, IngresoStock> vppf = save(ingresoStock);
		if(vppf.isSuccess()) {
			return new ResponseEntity<>(ingresoStock, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Texto>(new Texto(vppf.fail()), HttpStatus.CONFLICT);
		}
	}
		

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteParamPrefondeo(@PathVariable long id) {
		this.repo.delete(id);
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/eliminarLista", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> deleteList(@RequestBody SeleccionDto<FiltroIngresoStockDto> seleccion) {
		final Integer eliminados;
		if (seleccion.isInvertir()) {
			eliminados = paginador.deletePredicateExcludingIds(IngresoStock.class,
					(CriteriaBuilder cb, Root<IngresoStock> r) -> buildFilterPredicate(seleccion.getFiltro(), cb, r),
					seleccion.getIds(), IngresoStock_.id);
		} else {
			eliminados = paginador.deleteIds(IngresoStock.class, seleccion.getIds(), IngresoStock_.id);
		}
		return new ResponseEntity<>(eliminados, HttpStatus.ACCEPTED);
	}
	
	
	private Validation<String, IngresoStock> save(IngresoStock ingresoStock) {
			return Validation.success(repo.save(ingresoStock));
	}
	
	@RequestMapping(value = "/page")
	public ResponseEntity<List<IngresoStockDto>> getPage(@RequestHeader(value = "Range") String strRange, @RequestBody FiltroIngresoStockDto filtro) {
		return this.paginador.buildPage(IngresoStock.class,strRange, this.ingresoStockMapper::entityToDto,
				(CriteriaBuilder cb, Root<IngresoStock> r) -> buildFilterPredicate(filtro, cb, r));
	}
	
	private Predicate buildFilterPredicate(final FiltroIngresoStockDto filtro, CriteriaBuilder builder, Root<IngresoStock> root) {
		Predicate p = builder.conjunction();
		if (filtro != null) {
			
			if (filtro.getId() != null)
				p = builder.and(p, builder.equal(root.get(IngresoStock_.id), filtro.getId()));
			
			else{
				if (filtro.getDescripcion() != null){
				p = builder.and(p, builder.like(root.get(IngresoStock_.descripcion).as(String.class), filtro.getDescripcion()+"%"));}
			}
		}
		return p;
	}
		
	
	public static class FiltroIngresoStockDto {

		private Long id;
		private String descripcion;
		private int cantidad;

		
		public FiltroIngresoStockDto(){
			super();
		}
		
		public FiltroIngresoStockDto(Long id, String descripcion, int cantidad) {
			super();
			this.id = id;
			this.descripcion = descripcion;
			this.cantidad = cantidad;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		
		
	}
	
}
