package calaru.util.pager;

import java.util.List;

public class SeleccionDto<T> {
	private T filtro;
	private List<Long> ids;
	private boolean invertir;
	
	public SeleccionDto() {
		super();
	}
	public T getFiltro() {
		return filtro;
	}
	public void setFiltro(T filtro) {
		this.filtro = filtro;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public boolean isInvertir() {
		return invertir;
	}
	public void setInvertir(boolean invertir) {
		this.invertir = invertir;
	}
}