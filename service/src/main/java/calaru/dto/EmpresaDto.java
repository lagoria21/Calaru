package calaru.dto;


public class EmpresaDto {
	private long id;
	private String descripcion;
	private String abreviatura;
	
	public EmpresaDto() {
		super();
	}
	public EmpresaDto(long id, String descripcion, String abreviatura) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.abreviatura = abreviatura;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	
	@Override
	public String toString() {
		return "EmpresaDto(id=" + id + ", descripcion=" + descripcion + ", abreviatura=" + abreviatura + ")";
	}
	
	
}
