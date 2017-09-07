package calaru.webback;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import calaru.dto.IngresoStockDto;
import calaru.dto.Texto;
import calaru.dto.UserDto;
import calaru.mapper.Mapper;
import calaru.model.IngresoStock;
import calaru.model.IngresoStock_;
import calaru.model.User;
import calaru.model.User_;
import calaru.model.Users;
import calaru.repository.UserRepository;
import calaru.util.pager.Paginator;
import fj.data.Validation;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
		
	@Autowired
	Mapper<User, UserDto> altaUserMapper;
	
	@Autowired
	Mapper<Users, UserDto> listaUserMapper;
	
	@Autowired 
	UserRepository repo;
	
	@Autowired
	Paginator paginador;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> postIngreso(@RequestBody UserDto userDto) {
		Validation<String, User> vm = this.save(altaUserMapper.dtoToEntity(userDto));
		if(vm.isSuccess()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(
					ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vm.success().getId()).toUri());
			return new ResponseEntity<>(altaUserMapper.entityToDto(vm.success()), httpHeaders, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Texto>(new Texto(vm.fail()), HttpStatus.CONFLICT);
		}
	}
	
	private Validation<String, User> save(User user) {
		return Validation.success(repo.save(user));		
	}
	
	
	@RequestMapping(value = "/page")
	public ResponseEntity<List<UserDto>> getPage(@RequestHeader(value = "Range") String strRange, @RequestBody FiltroUserDto filtro) {
		return this.paginador.buildPage(Users.class,strRange, this.listaUserMapper::entityToDto,
				(CriteriaBuilder cb, Root<Users> r) -> buildFilterPredicate(filtro, cb, r));
	}
	
	private Predicate buildFilterPredicate(final FiltroUserDto filtro, CriteriaBuilder builder, Root<Users> root) {
		Predicate p = builder.conjunction();
		
		if (filtro != null) {
			
			if (filtro.getId() != null){
			//	p = builder.and(p, builder.equal(root.get(User_.id), filtro.getId()));
				}
			else{
				if (filtro.email != null){
			//	p = builder.and(p, builder.like(root.get(User_.email).as(String.class), filtro.email+"%"));
					}
			}
		}
		return p;
	}
	
	public static class FiltroUserDto {

		private Long id;
		private String email;
		private String name;

		
		public FiltroUserDto(){
			super();
		}
		
		public FiltroUserDto(Long id, String email, String name) {
			super();
			this.id = id;
			this.email = email;
			this.name = name;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		
		
	}
	
	
	
}
