package model;

import java.sql.Date;

public class DicaSeguranca {

	private long idDica;
	private boolean anonimo;
	private String descricao;
	private String dataDica;
	private Usuario usuario;
	private Endereco endereco;

	public DicaSeguranca() {
		super();
	}

	public DicaSeguranca(long idDica, boolean anonimo, String descricao, String dataDica, Usuario usuario, Endereco endereco) {
		super();
		this.idDica = idDica;
		this.anonimo = anonimo;
		this.descricao = descricao;
		this.dataDica = dataDica;
		this.usuario = usuario;
		this.endereco = endereco;
	}

	public long getIdDica() {
		return idDica;
	}

	public void setIdDica(long idDica) {
		this.idDica = idDica;
	}

	public boolean isAnonimo() {
		return anonimo;
	}

	public void setAnonimo(boolean anonimo) {
		this.anonimo = anonimo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataDica() {
		return dataDica;
	}

	public void setDataDica(String dataDica) {
		this.dataDica = dataDica;
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
				"\nIdDica: " + idDica + 
				"\nAnônimo: " + anonimo + 
				"\nDescrição: " + descricao + 
				"\nData da Dica: "+ dataDica + 
				"\nUsuário: " + usuario + 
				"\nEndereço: " + endereco + "\n";
	}

}
