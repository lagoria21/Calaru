package calaru.dto;

public class RespuestaBase<T> {
	private int resultado;
	private String descripcion;
	private T data;
	
	public RespuestaBase() {
		// TODO Auto-generated constructor stub
	}
	
	public RespuestaBase(int resultado, String descripcion){
		this.resultado = resultado;
		this.descripcion = descripcion;
	}
	
	public int getResultado() {
		return resultado;
	}
	
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	
}
