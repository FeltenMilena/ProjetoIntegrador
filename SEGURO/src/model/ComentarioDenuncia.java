package model;

public class ComentarioDenuncia {
	
	private long idDenComen;
	private Usuario usuario;
	private Denuncia denuncia;
	
	public ComentarioDenuncia() {
		super();
	}

	public ComentarioDenuncia(long idDenComen, Usuario usuario,
			Denuncia denuncia) {
		super();
		this.idDenComen = idDenComen;
		this.usuario = usuario;
		this.denuncia = denuncia;
	}

	public long getIdDenComen() {
		return idDenComen;
	}

	public void setIdDenComen(long idDenComen) {
		this.idDenComen = idDenComen;
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
				"\nIdDenunciaComentario: " + idDenComen + 
				"\nUsuário: " + usuario + 
				"\nDenúncia: " + denuncia + "\n";
	}

}
