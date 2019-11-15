package apresentacao;

import java.util.List;
import java.util.Scanner;
import model.*;
import persistencia.DenunciaDAO;
import persistencia.EnderecoDAO;
import persistencia.UsuarioDAO;

public class DenunciaCadastro {
	Scanner sc = new Scanner(System.in);
	Scanner teclado = new Scanner(System.in);
	Denuncia denuncia;
	DenunciaDAO denunciaDAO = new DenunciaDAO();
	List<Denuncia> listaDenuncia;
	Usuario usuario;
	Endereco endereco;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	EnderecoDAO enderecoDAO = new EnderecoDAO();
	long idDenuncia, idEndereco;
	boolean anonimo;
	String dataDenuncia;
	String dia;
	String mes, ano;
	String descricao, categoria, email;
	int escolha, nivelViolencia;

	public void denuncia() {
		do {
			System.out.println("1- Cadastrar " 
					+ "\n2- Editar " 
					+ "\n3- Listar " 
					+ "\n4- Excluir "
					+ "\n5- Buscar por Id" 
					+ "\n0- Voltar");
			escolha = sc.nextInt();
			if (escolha == 1) {
				System.out.println("Digite true se a denúnica for anônima, e false se não for anônima:");
				anonimo = sc.nextBoolean();
				System.out.println("Digite a data em que ocorreu: (dd mm aaaa)");
				dia = sc.next();
				mes = sc.next();
				ano = sc.next();
				dataDenuncia = new String (dia) + ("/") + (mes) + ("/") + (ano);
				System.out.println("Digite a descrição da denúncia:");
				descricao = teclado.nextLine();
				System.out.println("Digite de 1 à 5 o nível da violênica:");
				nivelViolencia = sc.nextInt();
				System.out.println("Digite em qual categoria se encaixa a denúncia. Ex: Assalto, Assassinato...");
				categoria = teclado.next();
				System.out.println("Digite o email do usuário já cadastrado:");
				email = sc.next();
				System.out.println("Digite o idEndereço cadastrado:");
				idEndereco = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				endereco = enderecoDAO.buscarPorId(idEndereco);
				denuncia = new Denuncia(0, anonimo, dataDenuncia, descricao, nivelViolencia, categoria, usuario, endereco);
				denunciaDAO.salvar(denuncia);

			} else if (escolha == 2) {
				System.out.println("Digite o idDenuncia para ser editado:");
				idDenuncia = sc.nextLong();
				System.out.println("Digite true se a denúncia for anônima e false se não for:");
				anonimo = sc.nextBoolean();
				System.out.println("Digite uma descrição nova, se necessário:");
				descricao = teclado.nextLine();
				System.out.println("Digite de 1 à 5 o nivél de violência, se necessário:");
				nivelViolencia = sc.nextInt();
				System.out.println("Digite uma nova categoria em que a denúncia se encaixa, se necessário:");
				categoria = teclado.nextLine();
				System.out.println("Digite o email do usuário já cadastrado:");
				email = sc.next();
				System.out.println("Digite o idEndereço cadastrado:");
				idEndereco = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				endereco = enderecoDAO.buscarPorId(idEndereco);
				denuncia = new Denuncia(idDenuncia, anonimo, dataDenuncia, descricao, nivelViolencia, categoria, usuario, endereco);
				denunciaDAO.salvar(denuncia);
			} else if (escolha == 3) {
				listaDenuncia = denunciaDAO.buscarTodos();
				for (Denuncia d : listaDenuncia) {
					System.out.println(d.toString());
				}
			} else if (escolha == 4) {
				System.out.println("Digite o idDenuncia para ser excluido:");
				idDenuncia = sc.nextLong();
				denunciaDAO.excluir(idDenuncia);
			}else if (escolha == 5) {
				System.out.println("Insira o idDenuncia para pesquisa");
				idDenuncia = sc.nextLong();
				denuncia = denunciaDAO.buscarPorId(idDenuncia);
				System.out.println(denuncia.toString());
			}
		} while (escolha != 0);
	}
}
