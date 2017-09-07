package calaru.dto;

import java.util.Set;

public class UserDto {

	private int id;
	private String email;
	private String password;
	private String name;
	private String lastName;
	private int active;
	private Set<RoleDto> roleDto;
	
	public UserDto()
	{		
	}
	
	
	public UserDto(int id, String email, String password, String name, String lastName, int active,
			int roles, Set<RoleDto> roleDto) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
		this.roleDto = roleDto;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}


	public Set<RoleDto> getRoleDto() {
		return roleDto;
	}


	public void setRoleDto(Set<RoleDto> roleDto) {
		this.roleDto = roleDto;
	}
	

	
	
}
