package calaru.webback;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
import calaru.model.Users_;
import calaru.repository.UserRepository;
import calaru.repository.UsersRepository;
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
	UsersRepository repos;
	
	@Autowired
	Paginator paginador;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public UserDto getIngreso(@PathVariable int id) {	
		return listaUserMapper.entityToDto(repos.findOne(id));
	}

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
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> putParamPrefondeo(@PathVariable long id, @RequestBody User users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		
		Validation<String, User> vppf = save(users);
		if(vppf.isSuccess()) {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Texto>(new Texto(vppf.fail()), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/modificaEstado/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> putAgregar(@PathVariable long id, @RequestBody User users) {
		
		User user = repo.findById(users.getId());
		user.setActive(users.getActive());
		
	/*	if(users.getActive() == 1){
			users.setActive(2);
		}
		else{
			users.setActive(1);
		}
		*/
		
		Validation<String, User> vppf = save(user);
		if(vppf.isSuccess()) {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Texto>(new Texto(vppf.fail()), HttpStatus.CONFLICT);
		}
	}
	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> put(@PathVariable long id, @RequestBody User users) {
		
		UserDto dto;
		
		dto = getBuscar(users.getId());
		
		Validation<String, User> vppf = save(users);
		if(vppf.isSuccess()) {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Texto>(new Texto(vppf.fail()), HttpStatus.CONFLICT);
		}
	}*/

	
	public UserDto getBuscar(int id) {	
		return listaUserMapper.entityToDto(repos.findOne(id));
	}
	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> deleteParamPrefondeo(@PathVariable long id) {
		this.repo.delete(id);
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}*/
	
	
	
	@RequestMapping(value = "/page")
	public ResponseEntity<List<UserDto>> getPage(@RequestHeader(value = "Range") String strRange, @RequestBody FiltroUserDto filtro) {
		return this.paginador.buildPage(Users.class,strRange, this.listaUserMapper::entityToDto,
				(CriteriaBuilder cb, Root<Users> r) -> buildFilterPredicate(filtro, cb, r));
	}
	
	private Predicate buildFilterPredicate(final FiltroUserDto filtro, CriteriaBuilder builder, Root<Users> root) {
		Predicate p = builder.conjunction();
		
		if (filtro != null) {
			
			if(filtro.getName() != null){
				p = builder.and(p, builder.like(root.get(Users_.name).as(String.class), filtro.name+"%"));
			//(filtro.getId() != null){
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
