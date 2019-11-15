package apresentacao;

import java.util.List;
import java.util.Scanner;

import model.AvaliacaoDenuncia;
import model.Denuncia;
import model.Usuario;
import persistencia.AvaliacaoDenunciaDAO;
import persistencia.DenunciaDAO;
import persistencia.UsuarioDAO;

public class AvaliacaoDenunciaCadastro {
	Scanner sc = new Scanner(System.in);
	AvaliacaoDenuncia avaliacaoDenuncia;
	AvaliacaoDenunciaDAO avaliacaoDenunciaDAO = new AvaliacaoDenunciaDAO();
	List<AvaliacaoDenuncia> listaAvaliacaoDenuncia;
	Denuncia denuncia = new Denuncia();
	DenunciaDAO denunciaDAO = new DenunciaDAO();
	Usuario usuario;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	int escolha;
	long idAvaliacaoDenuncia, idDenuncia;
	String email;
	public void avaliacaoDenuncia(){
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
				avaliacaoDenuncia = new AvaliacaoDenuncia(0, usuario, denuncia);
				avaliacaoDenunciaDAO.salvar(avaliacaoDenuncia);

			}else if(escolha == 2){
				System.out.println("Digite o idAvaliacaoDenuncia para ser editar:");
				idAvaliacaoDenuncia = sc.nextLong();
				System.out.println("Digite o email do usuário já cadastrado:");
				email = sc.next();
				System.out.println("Digite o idDenuncia cadastrado:");
				idDenuncia = sc.nextLong();
				usuario = usuarioDAO.buscarPorEmail(email);
				denuncia = denunciaDAO.buscarPorId(idDenuncia);
				avaliacaoDenuncia = new AvaliacaoDenuncia(idAvaliacaoDenuncia, usuario, denuncia);
				avaliacaoDenunciaDAO.editar(avaliacaoDenuncia);
			}else if(escolha == 3){
				listaAvaliacaoDenuncia = avaliacaoDenunciaDAO.buscarTodos();
				System.out.println("\nAvaliaçoes de Denuncias cadastradas no sistema: \n");
				for(AvaliacaoDenuncia a: listaAvaliacaoDenuncia){
					System.out.println(a.toString() + "\n");
				}
			}else if(escolha == 4){
				System.out.println("Digite o idAvaliacaoDenuncia para ser excluido");
				idAvaliacaoDenuncia = sc.nextLong();
				avaliacaoDenunciaDAO.excluir(idAvaliacaoDenuncia);
			}else if (escolha == 5) {
				System.out.println("Insira o idAvaliacaoDenuncia para pesquisa");
				idAvaliacaoDenuncia = sc.nextLong();
				avaliacaoDenuncia = avaliacaoDenunciaDAO.buscarPorId(idAvaliacaoDenuncia);
				System.out.println(avaliacaoDenuncia.toString());
			}
		}while(escolha != 0);
	}
}
