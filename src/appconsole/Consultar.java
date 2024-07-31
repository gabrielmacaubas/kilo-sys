package appconsole;

import modelo.Refeicao;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {
		try {
			Fachada.inicializar();

			System.out.println("consultas... \n");
			System.out.println("\nrefeições da data 10/05/2024");
			for(Refeicao r : Fachada.listarRefeicoesPorData("10/05/2024"))
				System.out.println(r);
			
			System.out.println("\nrefeições com pesagem maior que 1kg");
			for(Refeicao r : Fachada.listarRefeicoesComPesagemMaiorQue(1))
				System.out.println(r);
			
			System.out.println("\nrefeições com mais de 1 bebida");
			for(Refeicao r : Fachada.listarRefeicoesComMaisDeNBebidas(1))
				System.out.println(r);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

