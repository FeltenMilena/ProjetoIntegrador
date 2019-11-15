package apresentacao;

import java.util.List;
import java.util.Scanner;

import model.Endereco;
import model.Usuario;
import persistencia.EnderecoDAO;
import persistencia.UsuarioDAO;

public class UsuarioCadastro {
	Scanner sc = new Scanner(System.in);
	Scanner teclado = new Scanner(System.in);
	Usuario usuario;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	List<Usuario> listaUsuario;
	Endereco endereco;
	EnderecoDAO enderecoDAO = new EnderecoDAO();
	int escolha;
	long idEndereco;
	String email, nome, senha, emailParaEditar;

	public void usuario() {
		do {
			System.out.println("1- Cadastrar " 
					+ "\n2- Editar " 
					+ "\n3- Listar " 
					+ "\n4- Excluir "
					+ "\n5- Buscar por Email"
					+ "\n0- Voltar");
			escolha = sc.nextInt();
			if (escolha == 1) {
				System.out.println("Digite um email:");
				email = sc.next();
				System.out.println("Digite seu nome:");
				nome = teclado.nextLine();
				System.out.println("Digite uma senha:");
				senha = sc.next();
				System.out.println("Digite seu idEndereço cadastrado:");
				idEndereco = sc.nextLong();
				endereco = enderecoDAO.buscarPorId(idEndereco);
				usuario = new Usuario(email, nome, senha, endereco);
				usuarioDAO.salvar(usuario);
			} else if (escolha == 2) {
				System.out.println("Digite um email já cadastrado para editar:");
				emailParaEditar = sc.next();
				System.out.println("Digite seu nome novo, se necessário:");
				nome = teclado.nextLine();
				System.out.println("Digite uma nova senha, se necessário:");
				senha = sc.next();
				System.out.println("Digite o idEndereço cadastrado:");
				idEndereco = sc.nextLong();
				endereco = enderecoDAO.buscarPorId(idEndereco);
				usuario = new Usuario(email, nome, senha, endereco);
				usuarioDAO.editar(usuario, emailParaEditar);
			} else if (escolha == 3) {
				listaUsuario = usuarioDAO.buscarTodos();
				System.out.println("\nUsuários cadastrados no sistema: \n");
				for (Usuario u : listaUsuario) {
					System.out.println(u.toString() + "\n");
				}
			} else if (escolha == 4) {
				System.out.println("Digite o Email do Usuário para ser excluido");
				email = sc.next();
				usuarioDAO.excluir(email);
			} else if (escolha == 5) {
			System.out.println("Insira o Email para pesquisa");
			email = sc.next();
			usuario = usuarioDAO.buscarPorEmail(email);
			System.out.println(usuario.toString());
		}
		} while (escolha != 0);
	}
}
