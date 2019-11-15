package apresentacao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import persistencia.EnderecoDAO;
import model.Endereco;

public class EnderecoCadastro {
	Scanner sc = new Scanner(System.in);
	Scanner teclado = new Scanner(System.in);
	Endereco endereco;
	EnderecoDAO enderecoDAO = new EnderecoDAO();;
	List<Endereco> listaEnderecos;
	long idEndereco;
	int numero, escolha;
	String bairro, rua, complemento, cidade, estado;

	public void endereco() throws SQLException {
		do {
			System.out.println("1- Cadastrar " 
					+ "\n2- Editar "
					+ "\n3- Listar " 
					+ "\n4- Excluir " 
					+ "\n5- Buscar por Id"
					+ "\n0- Voltar");
			escolha = sc.nextInt();
			if (escolha == 1) {
				System.out.println("Digite o nome do bairro:");
				bairro = teclado.nextLine();
				System.out.println("Digite o n�mero da resid�ncia");
				numero = sc.nextInt();
				System.out.println("Digite o nome da rua:");
				rua = teclado.nextLine();
				System.out.println("Digite o complemento (caso n�o tenha, coloque apenas n�o existente)");
				complemento = teclado.nextLine();
				System.out.println("Digite o nome da cidade:");
				cidade = teclado.nextLine();
				System.out.println("Digite o nome do estado:");
				estado = teclado.nextLine();
				endereco = new Endereco(0, 0, bairro, numero, rua, complemento, cidade, estado);
				enderecoDAO.salvar(endereco);
			} else if (escolha == 2) {
				System.out.println("Digite o idEndere�o que ser� editado:");
				idEndereco = sc.nextLong();
				System.out.println("Digite o bairro novo, se necess�rio:");
				bairro = teclado.nextLine();
				System.out.println("Digite o novo n�mero da residencia, se necess�rio:");
				numero = sc.nextInt();
				System.out.println("Digite o novo nome da rua, se necess�rio:");
				rua = teclado.nextLine();
				System.out.println("Digite o novo complemento, se necess�rio:");
				complemento = teclado.nextLine();
				System.out.println("Digite o novo nome da cidade, se necess�rio:");
				cidade = teclado.nextLine();
				System.out.println("Digite o novo nome do estado, se necess�rio:");
				estado = teclado.nextLine();
				endereco = new Endereco(idEndereco, 0, bairro, numero, rua, complemento, cidade, estado);
				enderecoDAO.editar(endereco);
			} else if (escolha == 3) {
				listaEnderecos = enderecoDAO.buscarTodos();
				System.out.println("\nEndere�os cadastrados no sistema: \n");
				for (Endereco end : listaEnderecos) {
					System.out.println(end.toString() + "\n");
				}
			} else if (escolha == 4) {
				System.out.println("IdEndereco para ser excluido:");
				idEndereco = sc.nextLong();
				enderecoDAO.excluir(idEndereco);
			} else if (escolha == 5) {
				System.out.println("Insira o idEndereco para pesquisa");
				idEndereco = sc.nextLong();
				endereco = enderecoDAO.buscarPorId(idEndereco);
				System.out.println(endereco.toString());
			}
		} while (escolha != 0);
	}

}
