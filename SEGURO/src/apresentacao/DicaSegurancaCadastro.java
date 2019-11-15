package apresentacao;

import java.util.List;
import java.util.Scanner;
import model.*;
import persistencia.DicaSegurancaDAO;
import persistencia.EnderecoDAO;
import persistencia.UsuarioDAO;

public class DicaSegurancaCadastro {
	Scanner sc = new Scanner(System.in);
	Scanner teclado = new Scanner(System.in);
	DicaSeguranca dicaSeguranca = new DicaSeguranca();
	DicaSegurancaDAO dicaSegurancaDAO = new DicaSegurancaDAO();
	List<DicaSeguranca> listaDica;
	Usuario usuario;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	Endereco endereco;
	EnderecoDAO enderecoDAO = new EnderecoDAO();
	int escolha;
	long idDica, idEndereco;
	String descricao, email;
	boolean anonimo;
	String dataDica = new String();
	String dia, mes, ano;

	public void dicaSeguranca() {
		do {
			System.out.println("1- Cadastrar " 
					+ "\n2- Editar " 
					+ "\n3- Listar " 
					+ "\n4- Excluir "
					+ "\n5- Buscar por Id"
					+ "\n0- Voltar");
			escolha = sc.nextInt();
			if (escolha == 1) {
				System.out.println("Digite true se a dica é anonima e false se não é anonima:");
				anonimo = sc.nextBoolean();
				System.out.println("Digite a data em que ocorreu: (dd mm aaaa)");
				dia = sc.next();
				mes = sc.next();
				ano = sc.next();
				dataDica = new String (dia) + ("/") + (mes) + ("/") + (ano);
				descricao = teclado.nextLine();
				System.out.println("Digite o email do seu usuário já cadastrado:");
				email = sc.next();
				System.out.println("Digite o idEndereço cadastrado:");
				idEndereco = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				endereco = enderecoDAO.buscarPorId(idEndereco);
				dicaSeguranca = new DicaSeguranca(0, anonimo, descricao, dataDica, usuario, endereco);
				dicaSegurancaDAO.salvar(dicaSeguranca);
			} else if (escolha == 2) {
				System.out.println("Digite o idDica para ser editar:");
				idDica = sc.nextLong();
				System.out.println("Digite true se é uma dica anônima, e false se não for uma dica anônima:");
				anonimo = sc.nextBoolean();
				System.out.println("Digite a nova dica:");
				descricao = teclado.nextLine();
				System.out.println("Digite o email do seu usuário já cadastrado:");
				email = sc.next();
				System.out.println("Digite o idEndereço cadastrado:");
				idEndereco = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				endereco = enderecoDAO.buscarPorId(idEndereco);
				dicaSeguranca = new DicaSeguranca(idDica, anonimo, descricao, dataDica, usuario, endereco);
				dicaSegurancaDAO.salvar(dicaSeguranca);
			} else if (escolha == 3) {
				listaDica = dicaSegurancaDAO.buscarTodos();
				System.out.println("\nDicas de Segurança cadastrados no sistem: \n");
				for (DicaSeguranca d : listaDica) {
					System.out.println(d.toString() + "\n");
				}
			} else if (escolha == 4) {
				System.out.println("Digite o idDica para ser excluido:");
				idDica = sc.nextLong();
				dicaSegurancaDAO.excluir(idDica);
			}else if (escolha == 5) {
				System.out.println("Insira o idDica para pesquisa");
				idDica = sc.nextLong();
				dicaSeguranca = dicaSegurancaDAO.buscarPorId(idDica);
				System.out.println(dicaSeguranca.toString());
			}
		} while (escolha != 0);
	}
}
