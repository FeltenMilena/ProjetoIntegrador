package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String email;
	private String nome;
	private String senha;
	private Endereco endereco;
	private List<Denuncia> listaUsuarioDenuncia;
	private List<DicaSeguranca> listaUsuarioDica;
	
	public Usuario() {
		super();
		this.listaUsuarioDenuncia = new ArrayList<>();
		this.listaUsuarioDica = new ArrayList<>();
	}

	public Usuario(String email, String nome, String senha, Endereco endereco) {
		super();
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
				"\nE-mail: " + email +
				"\nNome: " + nome +
				"\nSenha: " + senha +
				"\n"+ endereco;
	}
}

	