package model;

import java.util.ArrayList;
import java.util.List;

public class Endereco {
	
	private long idEndereco;
	private double indicePerigo;
	private String bairro;
	private int numero;
	private String rua;
	private String complemento;
	private String cidade;
	private String estado;
	private List<DicaSeguranca> listaEnderecoSeguranca;
	private List<Usuario> listaEnderecoUsuario;
	private List<Denuncia> listaEnderecoDenuncia;
	
	public Endereco() {
		super();
		this.listaEnderecoSeguranca = new ArrayList<>();
		this.listaEnderecoUsuario = new ArrayList<>();
		this.listaEnderecoDenuncia = new ArrayList<>();
	}
	public Endereco(long idEndereco, double indicePerigo, String bairro, int numero, String rua, String complemento, String cidade, String estado) {
		super();
		this.idEndereco = idEndereco;
		this.indicePerigo = indicePerigo;
		this.bairro = bairro;
		this.numero = numero;
		this.rua = rua;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
	}
	public long getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}
	public double getIndicePerigo() {
		return indicePerigo;
	}
	public void setIndicePerigo(double indicePerigo) {
		this.indicePerigo = indicePerigo;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String toString() {
		return 
				"\nIdEndereco: " + idEndereco +
				"\nIndice de Perigo: " + indicePerigo + 
				"\nBairro: " + bairro + 
				"\nNúmero: " + numero + 
				"\nRua: " + rua + 
				"\nComplemento: " + complemento + 
				"\nCidade: " + cidade+ 
				"\nEstado: " + estado +"\n";
	}
}
