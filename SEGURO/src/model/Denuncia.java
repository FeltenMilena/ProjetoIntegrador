package model;

public class Denuncia {
	
	private long idDenuncia;
	private boolean anonimo;
	private String dataDenuncia;
	private String descricao;
	private int nivelViolencia;
	private String categoria;
	private Usuario usuario;
	private Endereco endereco;
	
	public Denuncia() {
		super();
	}

	public Denuncia(long idDenuncia, boolean anonimo, String dataDenuncia, String descricao, int nivelViolencia, String categoria, Usuario usuario, Endereco endereco) {
		super();
		this.idDenuncia = idDenuncia;
		this.anonimo = anonimo;
		this.dataDenuncia = dataDenuncia;
		this.descricao = descricao;
		this.nivelViolencia = nivelViolencia;
		this.categoria = categoria;
		this.usuario = usuario;
		this.endereco = endereco;
	}

	public long getIdDenuncia() {
		return idDenuncia;
	}

	public void setIdDenuncia(long idDenuncia) {
		this.idDenuncia = idDenuncia;
	}

	public boolean isAnonimo() {
		return anonimo;
	}

	public void setAnonimo(boolean anonimo) {
		this.anonimo = anonimo;
	}

	public String getDataDenuncia() {
		return dataDenuncia;
	}

	public void setDataDenuncia(String dataDenuncia) {
		this.dataDenuncia = dataDenuncia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNivelViolencia() {
		return nivelViolencia;
	}

	public void setNivelViolencia(int nivelViolencia) {
		this.nivelViolencia = nivelViolencia;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return 
				"\nIdDenuncia: " + idDenuncia + 
				"\nAnônimo: " + anonimo + 
				"\nData da Denúncia: " + dataDenuncia+
				"\nDescrição: " + descricao + 
				"\nNivel de Violencia: " + nivelViolencia + 
				"\nCategoria: " + categoria+
				"\nUsuário: " + usuario + 
				"\nEndereço: " + endereco + "\n";
	}
	
}