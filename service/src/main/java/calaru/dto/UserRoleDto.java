package calaru.dto;

public class UserRoleDto {
	
    private Long userroleid;
	private Long userid;
	private String role;
	
	public UserRoleDto()
	{
		
	}
	
	public UserRoleDto(Long userroleid, Long userid, String role) {
		super();
		this.userroleid = userroleid;
		this.userid = userid;
		this.role = role;
	}
	
	public Long getUserroleid() {
		return userroleid;
	}
	public void setUserroleid(Long userroleid) {
		this.userroleid = userroleid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
