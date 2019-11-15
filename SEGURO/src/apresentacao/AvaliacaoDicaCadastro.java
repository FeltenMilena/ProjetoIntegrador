package apresentacao;

import java.util.List;
import java.util.Scanner;

import model.*;
import persistencia.AvaliacaoDicaDAO;
import persistencia.DicaSegurancaDAO;
import persistencia.UsuarioDAO;

public class AvaliacaoDicaCadastro {
	Scanner sc = new Scanner(System.in);
	AvaliacaoDica avaliacaoDica;
	AvaliacaoDicaDAO avaliacaoDicaDAO = new AvaliacaoDicaDAO();
	List<AvaliacaoDica> listaAvaliacaoDica;;
	DicaSeguranca dica;
	DicaSegurancaDAO dicaDAO;
	Usuario usuario;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	int escolha;
	long idAvaliacaoDica, idDica;
	String email;
	public void avaliacaoDica(){
		do{
			System.out.println("1- Cadastrar "
					+ "\n2- Editar "
					+ "\n3- Listar "
					+ "\n4- Excluir "
					+ "\n5- Buscar por Id"
					+ "\n0- Voltar");
			escolha=sc.nextInt();
			if(escolha == 1){
				System.out.println("Digite o email do usuário já cadastrado:");
				email = sc.next();
				System.out.println("Digite o idDica cadastrado:");
				idDica = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				dica = dicaDAO.buscarPorId(idDica);
				avaliacaoDica = new AvaliacaoDica(0, usuario, dica);
				avaliacaoDicaDAO.salvar(avaliacaoDica);
			}else if(escolha == 2){
				System.out.println("Digite o idAvaliaçãoDica para ser editar:");
				idAvaliacaoDica = sc.nextLong();
				System.out.println("Digite o email do usuário já cadastrado:");
				email = sc.next();
				System.out.println("Digite o idDica cadastrado:");
				idDica = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				dica = dicaDAO.buscarPorId(idDica);
				avaliacaoDica = new AvaliacaoDica(idAvaliacaoDica, usuario, dica);
				avaliacaoDicaDAO.editar(avaliacaoDica);
			}else if(escolha == 3){
				listaAvaliacaoDica = avaliacaoDicaDAO.buscarTodos();
				System.out.println("\nAvaliações de Dicas cadastradas no sistema: \n");
				for(AvaliacaoDica a : listaAvaliacaoDica){
					System.out.println(a.toString() + "\n");
				}
			}else if(escolha == 4){
				System.out.println("Digite o idAvaliacaoDica para ser excluido:");
				idAvaliacaoDica = sc.nextLong();
				avaliacaoDicaDAO.excluir(idAvaliacaoDica);
			}else if (escolha == 5) {
				System.out.println("Insira o idAvaliacaoDica para pesquisa");
				idAvaliacaoDica = sc.nextLong();
				avaliacaoDica = avaliacaoDicaDAO.buscarPorId(idAvaliacaoDica);
				System.out.println(avaliacaoDica.toString());
			}
		}while(escolha != 0);
	}
}
