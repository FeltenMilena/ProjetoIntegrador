package apresentacao;

import java.util.List;
import java.util.Scanner;
import model.*;
import persistencia.ComentarioDicaDAO;
import persistencia.DicaSegurancaDAO;
import persistencia.UsuarioDAO;

public class ComentarioDicaCadastro {
	Scanner sc = new Scanner(System.in);
	ComentarioDica comentarioDica;
	ComentarioDicaDAO comentarioDicaDAO = new ComentarioDicaDAO();
	List<ComentarioDica> listaComentarioDica;
	DicaSeguranca dica;
	DicaSegurancaDAO dicaDAO = new DicaSegurancaDAO();
	Usuario usuario;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	int escolha;
	long idComentarioDica, idDica;
	String email;
	public void comentarioDica(){
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
				comentarioDica = new ComentarioDica(0, usuario, dica);
				comentarioDicaDAO.salvar(comentarioDica);
			}else if(escolha == 2){
				System.out.println("Digite o idComentarioDica para editar:");
				idComentarioDica = sc.nextLong();
				System.out.println("Digite o email:");
				email = sc.next();
				System.out.println("Digite o idDica:");
				idDica = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				dica = dicaDAO.buscarPorId(idDica);
				comentarioDica = new ComentarioDica(idComentarioDica, usuario, dica);
				comentarioDicaDAO.editar(comentarioDica);
			}else if(escolha == 3){
				listaComentarioDica = comentarioDicaDAO.buscarTodos();
				System.out.println("\nComentario de Dica cadastrados no sistema: \n");
				for(ComentarioDica c : listaComentarioDica){
					System.out.println(c.toString() + "\n");
				}
			}else if(escolha == 4){
				System.out.println("Digite o idComentario para ser excluido:");
				idComentarioDica = sc.nextLong();
				comentarioDicaDAO.excluir(idComentarioDica);
			}else if (escolha == 5) {
				System.out.println("Insira o idComentarioDica para pesquisa");
				idComentarioDica = sc.nextLong();
				comentarioDica = comentarioDicaDAO.buscarPorId(idComentarioDica);
				System.out.println(comentarioDica.toString());
			}
		}while(escolha != 0);
	}
}
