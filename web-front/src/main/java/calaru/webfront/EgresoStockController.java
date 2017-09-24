package calaru.webfront;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import calaru.dto.IngresoStockDto;
import calaru.dto.OrdenDeTrabajoDto;
import calaru.mapper.Mapper;
import calaru.model.IngresoStock;
import calaru.model.IngresoStock_;
import calaru.model.OrdenDeTrabajo;
import calaru.model.OrdenDeTrabajo_;
import calaru.util.pager.Paginator;
import calaru.webfront.IngresoStockController.FiltroIngresoStockDto;

@RestController
@RequestMapping("/egresos")
public class EgresoStockController {
	
	@Autowired
	Mapper<OrdenDeTrabajo, OrdenDeTrabajoDto> egresoMapper;
	
	@Autowired
	Paginator paginador;
	
	
	@RequestMapping(value = "/page")
	public ResponseEntity<List<OrdenDeTrabajoDto>> getPage(@RequestHeader(value = "Range") String strRange, @RequestBody FiltroEgresoDto filtro) {
		return this.paginador.buildPage(OrdenDeTrabajo.class,strRange, this.egresoMapper::entityToDto,
				(CriteriaBuilder cb, Root<OrdenDeTrabajo> r) -> buildFilterPredicate(filtro, cb, r));
	}
	
	private Predicate buildFilterPredicate(final FiltroEgresoDto filtro, CriteriaBuilder builder, Root<OrdenDeTrabajo> root) {
		Predicate p = builder.conjunction();
		if (filtro != null) {
			
			if (filtro.getId() != null)
				p = builder.and(p, builder.equal(root.get(OrdenDeTrabajo_.id), filtro.getId()));
			else{
				if (filtro.getResponsable() != null){
					p = builder.and(p, builder.like(root.get(OrdenDeTrabajo_.responsable).as(String.class), filtro.getResponsable()+"%"));
				}
				else
				{
					
					if (filtro.getFecha() != null){
						p = builder.and(p, builder.like(root.get(OrdenDeTrabajo_.fecha).as(String.class), filtro.getFecha()+"%"));
					}
				}
			}	

		}	
		return p;
	
	
}
		
	public static class FiltroEgresoDto {

		private Long id;
		private String responsable;
		private LocalDate fecha;

		
		public FiltroEgresoDto(){
			super();
		}
		
		public FiltroEgresoDto(Long id, String responsable, LocalDate fecha) {
			super();
			this.id = id;
			this.responsable = responsable;
			this.fecha = fecha;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getResponsable() {
			return responsable;
		}

		public void setNombre(String responsable) {
			this.responsable = responsable;
		}

		public LocalDate getFecha() {
			return fecha;
		}

		public void setFecha(LocalDate fecha) {
			this.fecha = fecha;
		}
		
	}
   
}