package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();
			
			System.out.println("cadastrando pesagens...");
			Fachada.cadastrarPesagem(1, "Carne", 15.0, 0.250);
			Fachada.cadastrarPesagem(2, "Frango", 10.0, 1.200);
			Fachada.cadastrarPesagem(3, "Salmão", 50.0, 0.300);
			Fachada.cadastrarPesagem(4, "Salada", 8.0, 0.150);
			
			System.out.println("cadastrando bebidas...");
			Fachada.cadastrarBebida(5, "Refrigerante", 5.0, 0.350);
	        Fachada.cadastrarBebida(6, "Suco", 7.0, 0.300);
	        Fachada.cadastrarBebida(7, "Água", 3.0, 0.500);
	        Fachada.cadastrarBebida(8, "Cerveja", 10.0, 0.355);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
	        Fachada.cadastrarRefeicao(1, "10/05/2024");
	        Fachada.cadastrarRefeicao(2, "10/05/2024");
	        Fachada.cadastrarRefeicao(3, "12/05/2024");
	        Fachada.cadastrarRefeicao(4, "13/05/2024");
			
	        System.out.println("Adicionando consumos nas refeições...");
	        Fachada.adicionarConsumoNaRefeicao(1, 1);
	        Fachada.adicionarConsumoNaRefeicao(2, 5);
	        Fachada.adicionarConsumoNaRefeicao(3, 2);
	        Fachada.adicionarConsumoNaRefeicao(3, 6);
	        Fachada.adicionarConsumoNaRefeicao(4, 3);
	        Fachada.adicionarConsumoNaRefeicao(4, 4);
	        Fachada.adicionarConsumoNaRefeicao(4, 7);
	        Fachada.adicionarConsumoNaRefeicao(4, 8);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}
