package calaru.web;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

//import com.ayi.util.ad.ADUtility;

//import calaru.model.RolEmpresaUsuario;


public class UserAuthentication  implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4628926005962437402L;
	
	private String name;
	private String password;
	private List<GrantedAuthority> authorities;
	//private RolEmpresaUsuario rolEmpresaUsuario;
	private boolean authenticated;
	private String logo;
	
	
	
	public UserAuthentication(String name, String password,String hostname,String port,String sslport,String securityPrincipal,String securityPassword,String userBase) {
		super();
		this.name = name;
		this.password = password;
		
		try {

		} catch(Exception e) {
			
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		authenticated=arg0;
	}
	

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	public RolEmpresaUsuario getRolEmpresaUsuario() {
//		return rolEmpresaUsuario;
//	}

//	public void setRolEmpresaUsuario(RolEmpresaUsuario rolEmpresaUsuario) {
//		this.rolEmpresaUsuario = rolEmpresaUsuario;
//	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

//	public String authenticatedAD() {
//		int authentication=adUtility.authenticate(name, password);
//		
//		String error=null;
//		
//		switch (authentication) {
//			case ADUtility.COD_AUTENTICATION_OK:
//				error = "OK";
//				break;
//			case ADUtility.COD_CUENTA_BLOQUEADA:
//				error = "Usuario bloqueado.";
//				break;
//			case ADUtility.COD_CUENTA_DESHABILITADA:
//				error = "Usuario deshabilitado.";
//				break;
//			case ADUtility.COD_CUENTA_EXPIRADA:
//				error = "Su cuenta ha sido experida.";
//				break;
//			case ADUtility.COD_ERROR_DESCONOCIDO:
//				error = "Ocurrio un error.";
//				break;
//			case ADUtility.COD_NO_PERMITIDO:
//				error = "Usuario y/o contraseña invalida.";
//				break;
//			case ADUtility.COD_PASSWORD_EXPIRADA:
//				error = "Contraseña ha sido expirada.";
//				break;
//			case ADUtility.COD_PASSWORD_INVALIDA:
//				error = "Usuario y/o contraseña invalida.";
//				break;
//			case ADUtility.COD_PASSWORD_NECESITA_RESET:
//				error = "Debe cambiar su contraseña.";
//				break;
//			case ADUtility.COD_USUARIO_NO_ENCONTRADO:
//				error = "Usuario y/o contraseña invalida.";
//				break;
//		}
//		
////		return "OK";
//		return error;
//	}
//	

}
