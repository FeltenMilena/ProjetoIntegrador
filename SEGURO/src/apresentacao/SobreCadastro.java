package apresentacao;

import java.util.Scanner;

public class SobreCadastro {
	Scanner sc = new Scanner (System.in);
	int escolha;

	public void sobre() {
		
		do {
			System.out.println("\nNosso Projeto tem como objetivo listar o indice de perigo dos bairros."
					+ "\nPara que o projeto funcione corretamente é de extrema importância que seja cadastrado"
					+ "\nprimeiro o Endereço, e para ser efetuado alguma denúncia precisara ser um usuário. ");
			System.out.println("0- Voltar");
			escolha = sc.nextInt();
			
		}while (escolha != 0);
		
	}

}
