package calaru.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stock")
public class IngresoStock {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="id")
	private long id;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="fecha_ingreso")
	private LocalDate fechaIngreso;
	
	@Column(name="proveedor")
	private String proveedor;
	
	@Column(name="cuit")
	private int cuit;
	
	@Column(name="sector")
	private String sector;
	
	@Column(name="ubicacion")
	private String ubicacion;

	@Column(name="codigo")
	private int codigo;
	
	@Column(name="cantidad_minima")
	private int cantidadMinima;
	
	@Column(name="cantidad_maxima")
	private int cantidadMaxima;
	
	@Column(name="precio_unitario")
	private BigDecimal precioUnitario;
	
	@Column(name="ingreso_producto")
	private String ingresoProducto;
	
	
	public IngresoStock() {
		super();

	}

	public IngresoStock(long id, String descripcion, int cantidad,
			LocalDate fechaIngreso, String proveedor, int cuit, String sector,
			String ubicacion, int codigo, int cantidadMinima, int cantidadMaxima, BigDecimal precioUnitario, String ingresoProducto) {
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
		this.ingresoProducto = ingresoProducto;
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

	public String getIngresoProducto() {
		return ingresoProducto;
	}

	public void setIngresoProducto(String ingresoProducto) {
		this.ingresoProducto = ingresoProducto;
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

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	
}
