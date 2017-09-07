package calaru.model;

public enum TipoEmpresa {
	F("Financiera"), P("Proveedora");
	
	private String etiqueta;

	TipoEmpresa(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	public String getEtiqueta() {
		return this.etiqueta;
	};
}
