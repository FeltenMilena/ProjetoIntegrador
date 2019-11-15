package apresentacao;
import java.sql.SQLException;
import java.util.*;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) throws SQLException{
		sc = new Scanner(System.in);
		int escolha;
		AvaliacaoDenunciaCadastro avaliacaoDenuncia = new AvaliacaoDenunciaCadastro();
		AvaliacaoDicaCadastro avaliacaoDica = new AvaliacaoDicaCadastro();
	
		
		ComentarioDenunciaCadastro comentarioDenuncia = new ComentarioDenunciaCadastro();
		ComentarioDicaCadastro comentarioDica = new ComentarioDicaCadastro();
		DenunciaCadastro denuncia = new DenunciaCadastro();
		DicaSegurancaCadastro dicaSeguranca = new DicaSegurancaCadastro();
		EnderecoCadastro endereco = new EnderecoCadastro();
		UsuarioCadastro usuario = new UsuarioCadastro();
		SobreCadastro sobre = new SobreCadastro();
		
		System.out.println("			Bem vindo ao Sapucaia Alerta!!!"
				+ "\nDigite o número correspondente da função e você será direcionada a ela."
				+ "\nCaso não tenha preenchido o 7- Endereço, deverá ser feito por primeiro.\n");
		do{
			System.out.println("1- Avaliação da denúncia "
					+ "\n2- Avaliação da dica "
					+ "\n3- Comentário da denúncia "
					+ "\n4- Comentário da dica "
					+ "\n5- Denúncia "
					+ "\n6- Dica de segurança "
					+ "\n7- Endereço "
					+ "\n8- Usuário"
					+ "\n9- Sobre"
					+ "\n0- Sair");
			escolha = sc.nextInt();
			if(escolha == 1){
				avaliacaoDenuncia.avaliacaoDenuncia();
			}else if(escolha == 2){
				avaliacaoDica.avaliacaoDica();
			}else if(escolha == 3){
				comentarioDenuncia.comentarioDenuncia();
			}else if(escolha == 4){
				comentarioDica.comentarioDica();
			}else if(escolha == 5){
				denuncia.denuncia();
			}else if(escolha == 6){
				dicaSeguranca.dicaSeguranca();
			}else if(escolha == 7){
				endereco.endereco();
			}else if(escolha == 8){
				usuario.usuario();
			}else if(escolha == 9) {
				sobre.sobre();
			}
		}while(escolha != 0);
		System.out.println("Até mais, espero ter lhe ajudado em algo. Volte sempre.");
	}
}
