package calaru.dto;

public class ConsultaDto {
	
	private String codigo;
	private String descripcion;
	private String precioUnitario;
	private String precioTotal;
	private String stockMinimo;
	private String stockActual;
	
	public ConsultaDto() {
		super();
	}

	public ConsultaDto(String codigo, String descripcion, String precioUnitario, String precioTotal,
			String stockMinimo, String stockActual) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.precioTotal = precioTotal;
		this.stockMinimo = stockMinimo;
		this.stockActual = stockActual;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getPrecioUnitario() {
		return precioUnitario;
	}
	
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public String getPrecioTotal() {
		return precioTotal;
	}
	
	public void setPrecioTotal(String precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	public String getStockMinimo() {
		return stockMinimo;
	}
	
	public void setStockMinimo(String stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	
	public String getStockActual() {
		return stockActual;
	}
	
	public void setStockActual(String stockActual) {
		this.stockActual = stockActual;
	}

	
}
