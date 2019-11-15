package model;

public class ComentarioDica {
	private long idDicaComen;
	private Usuario usuario;
	private DicaSeguranca dicaSeguranca;
	
	public ComentarioDica() {
		super();
	}

	public ComentarioDica(long idDicaComen, Usuario usuario, DicaSeguranca dicaSeguranca) {
		super();
		this.idDicaComen = idDicaComen;
		this.usuario = usuario;
		this.dicaSeguranca = dicaSeguranca;
	}

	public long getIdDicaComen() {
		return idDicaComen;
	}

	public void setIdDicaComen(long idDicaComen) {
		this.idDicaComen = idDicaComen;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DicaSeguranca getDicaSeguranca() {
		return dicaSeguranca;
	}

	public void setDicaSeguranca(DicaSeguranca dicaSeguranca) {
		this.dicaSeguranca = dicaSeguranca;
	}

	@Override
	public String toString() {
		return 
				"\nIdDicaComentario: " + idDicaComen + 
				"\nUsuário: " + usuario + 
				"\nDicaSeguranca: "+ dicaSeguranca + "\n";
	}
	

}
