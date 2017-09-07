package calaru.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orden_de_trabajo")
public class OrdenDeTrabajo {
			
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)    
	    @Column(name="id")
		private long id;
		
		@Column(name="sector")
		private String sector;
		
		@Column(name="responsable")
		private String responsable;
		
		@Column(name="equipo")
		private String equipo;
		
		@Column(name="fecha")
		private LocalDate fecha;
		
		@Column(name="orden")
		private String orden;
		
		@Column(name="tarea")
		private String tarea;

		@Column(name="tiempo")
		private String tiempo;

		@Column(name="herramienta")
		private String herramienta;
		
		

		public OrdenDeTrabajo() {
			super();
		}
		
		

		public OrdenDeTrabajo(long id, String sector, String responsable,
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
