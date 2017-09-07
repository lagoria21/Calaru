package calaru.webfront;

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

import calaru.dto.ConsultaDto;
import calaru.mapper.Mapper;
import calaru.model.IngresoStock;
import calaru.model.IngresoStock_;
import calaru.util.pager.Paginator;

@RestController
@RequestMapping("/consultaStock")
public class ConsultaController {
	
	@Autowired
	Mapper<IngresoStock, ConsultaDto> mapperConsulta;
	
	@Autowired
	Paginator paginador;
	
	@RequestMapping(value = "/page")
	public ResponseEntity<List<ConsultaDto>> getPage(@RequestHeader(value = "Range") String strRange, @RequestBody FiltroConsultaStockDto filtro) {
		return this.paginador.buildPage(IngresoStock.class,strRange, this.mapperConsulta::entityToDto,
				(CriteriaBuilder cb, Root<IngresoStock> r) -> buildFilterPredicate(filtro, cb, r));
	}
	
	private Predicate buildFilterPredicate(final FiltroConsultaStockDto filtro, CriteriaBuilder builder, Root<IngresoStock> root) {
		Predicate p = builder.conjunction();
		if (filtro != null) {
			
			if (filtro.getId() != null)
				p = builder.and(p, builder.equal(root.get(IngresoStock_.id), filtro.getId()));
			
			else{
				if (filtro.getDescripcion() != null){
				p = builder.and(p, builder.like(root.get(IngresoStock_.descripcion).as(String.class), filtro.getDescripcion()+"%"));}
				//p = builder.and(p, builder.equal(root.get(IngresoStock_.id), filtro.getDescripcion()));
			}
		}
		return p;
	}
	
	
	
	public static class FiltroConsultaStockDto {

		private Long id;
		private String descripcion;

		
		public FiltroConsultaStockDto(){
			super();
		}
		
		public FiltroConsultaStockDto(Long id, String descripcion) {
			super();
			this.id = id;
			this.descripcion = descripcion;
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

		
		
	}
	
	
	

}
