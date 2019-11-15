package apresentacao;

import java.util.List;
import java.util.Scanner;
import model.*;
import persistencia.ComentarioDenunciaDAO;
import persistencia.DenunciaDAO;
import persistencia.UsuarioDAO;

public class ComentarioDenunciaCadastro {
	Scanner sc = new Scanner(System.in);
	ComentarioDenuncia comentarioDenuncia;
	ComentarioDenunciaDAO comentarioDenunciaDAO = new ComentarioDenunciaDAO();
	List<ComentarioDenuncia> listaComentarioDenuncia;
	Denuncia denuncia;
	DenunciaDAO denunciaDAO = new DenunciaDAO();
	Usuario usuario;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	int escolha;
	long idComentarioDenuncia, idDenuncia;
	String email;
	public void comentarioDenuncia(){
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
				System.out.println("Digite o idDenuncia cadastrado:");
				idDenuncia = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				denuncia = denunciaDAO.buscarPorId(idDenuncia);
				comentarioDenuncia = new ComentarioDenuncia(0, usuario, denuncia);
				comentarioDenunciaDAO.salvar(comentarioDenuncia);
			}else if(escolha == 2){
				System.out.println("Digite o idComentario denuncia para editar:");
				idComentarioDenuncia = sc.nextLong();
				System.out.println("Digite o email do usuário já cadastrado:");
				email = sc.next();
				System.out.println("Digite o idDenuncia cadastrado:");
				idDenuncia = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				denuncia = denunciaDAO.buscarPorId(idDenuncia);
				comentarioDenuncia = new ComentarioDenuncia(idComentarioDenuncia, usuario, denuncia);
				comentarioDenunciaDAO.salvar(comentarioDenuncia);
			}else if(escolha == 3){
				listaComentarioDenuncia = comentarioDenunciaDAO.buscarTodos();
				System.out.println("\nComentarios de Denúncia cadastrados no sistema: \n");
				for(ComentarioDenuncia c : listaComentarioDenuncia){
					System.out.println(c.toString() + "\n");
				}
			}else if(escolha == 4){
				System.out.println("Digite o idComentarioDenuncia para ser excluido:");
				idComentarioDenuncia = sc.nextLong();
				comentarioDenunciaDAO.excluir(idComentarioDenuncia);
			}else if (escolha == 5) {
				System.out.println("Insira o idComentarioDenuncia para pesquisa");
				idComentarioDenuncia = sc.nextLong();
				comentarioDenuncia = comentarioDenunciaDAO.buscarPorId(idComentarioDenuncia);
				System.out.println(comentarioDenuncia.toString());
			}
		}while(escolha != 0);
	}
}
