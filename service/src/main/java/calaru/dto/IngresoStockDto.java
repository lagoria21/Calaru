package calaru.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IngresoStockDto {

	private long id;
	private String descripcion;
	private int cantidad;
	private LocalDate fechaIngreso;
	private String proveedor;
	private int cuit;
	private String sector;
	private String ubicacion;
	private int codigo;
	private int cantidadMinima;
	private int cantidadMaxima;
	private BigDecimal precioUnitario;
	
	
	public IngresoStockDto(long id, String descripcion, int cantidad,
			LocalDate fechaIngreso, String proveedor, int cuit, String sector,
			String ubicacion,int codigo, int cantidadMinima, int cantidadMaxima, BigDecimal precioUnitario) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.proveedor = proveedor;
		this.cuit = cuit;
		this.sector = sector;
		this.ubicacion = ubicacion;
		this.codigo = codigo;
		this.cantidadMinima = cantidadMinima;
		this.cantidadMaxima = cantidadMaxima;
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public IngresoStockDto() {
		super();		
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
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public String getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	public int getCuit() {
		return cuit;
	}
	
	public void setCuit(int cuit) {
		this.cuit = cuit;
	}
	
	public String getSector() {
		return sector;
	}
	
	public void setSector(String sector) {
		this.sector = sector;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getCodigo()
	{
		return codigo;
	}
	
	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}

	public int getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(int cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public int getCantidadMaxima() {
		return cantidadMaxima;
	}

	public void setCantidadMaxima(int cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}
	
	
	
}
