package model;

public class AvaliacaoDica {
	
	private long idDicaAvalia;
	private Usuario usuario;
	private DicaSeguranca dicaSeguranca;
	
	public AvaliacaoDica() {
		super();
	}

	public AvaliacaoDica(long idDicaAvalia, Usuario usuario, DicaSeguranca dicaSeguranca) {
		super();
		this.idDicaAvalia = idDicaAvalia;
		this.usuario = usuario;
		this.dicaSeguranca = dicaSeguranca;
	}

	public long getIdDicaAvalia() {
		return idDicaAvalia;
	}

	public void setIdDicaAvalia(long idDicaAvalia) {
		this.idDicaAvalia = idDicaAvalia;
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
				"\nIdDicaAvalia: " + idDicaAvalia + 
				"\nUsuário: " + usuario + 
				"\nDicaSeguranca: "+ dicaSeguranca + "\n";
	}

}
