package apresentacao;

import java.util.Scanner;

public class SobreCadastro {
	Scanner sc = new Scanner (System.in);
	int escolha;

	public void sobre() {
		
		do {
			System.out.println("\nNosso Projeto tem como objetivo listar o indice de perigo dos bairros."
					+ "\nPara que o projeto funcione corretamente � de extrema import�ncia que seja cadastrado"
					+ "\nprimeiro o Endere�o, e para ser efetuado alguma den�ncia precisara ser um usu�rio. ");
			System.out.println("0- Voltar");
			escolha = sc.nextInt();
			
		}while (escolha != 0);
		
	}

}
