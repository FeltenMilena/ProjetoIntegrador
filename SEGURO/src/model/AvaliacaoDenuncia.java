package model;

public class AvaliacaoDenuncia {
	
	private long idDenAvalia;
	private Usuario usuario;
	private Denuncia denuncia;
	
	public AvaliacaoDenuncia() {
		super();
	}

	public AvaliacaoDenuncia(long idDenAvalia, Usuario usuario, Denuncia denuncia) {
		super();
		this.idDenAvalia = idDenAvalia;
		this.usuario = usuario;
		this.denuncia = denuncia;
	}

	public long getIdDenAvalia() {
		return idDenAvalia;
	}

	public void setIdDenAvalia(long idDenAvalia) {
		this.idDenAvalia = idDenAvalia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Denuncia getDenuncia() {
		return denuncia;
	}

	public void setDenuncia(Denuncia denuncia) {
		this.denuncia = denuncia;
	}

	@Override
	public String toString() {
		return 
				"\nIdDenunciaAvaliacao: " + idDenAvalia + 
				"\nUsuário: " + usuario + 
				"\nDenúncia: " + denuncia+ "\n";
	}
	
}
