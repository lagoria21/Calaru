package calaru.dto;

import java.time.LocalDate;



public class OrdenDeTrabajoDto {

	private long id;
	private String sector;
	private String responsable;
	private String equipo;
	private LocalDate fecha;
	private String orden;
	private String tarea;
	private String tiempo;
	private String herramienta;

	public OrdenDeTrabajoDto() {
		super();
	}

	public OrdenDeTrabajoDto(long id, String sector, String responsable,
			String equipo, LocalDate fecha, String orden, String tarea,
			String tiempo, String herramienta) {
		super();
		this.id = id;
		this.sector = sector;
		this.responsable = responsable;
		this.equipo = equipo;
		this.fecha = fecha;
		this.orden = orden;
		this.tarea = tarea;
		this.tiempo = tiempo;
		this.herramienta = herramienta;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getHerramienta() {
		return herramienta;
	}

	public void setHerramienta(String herramienta) {
		this.herramienta = herramienta;
	}
	
	
}
