package appconsole;

import jakarta.persistence.EntityManager;
import regras_negocio.Fachada;

public class Cadastrar {
	protected static EntityManager manager;

	public Cadastrar() {
		try {
			Fachada.inicializar();
			
			System.out.println("cadastrando pesagens...");
			Fachada.cadastrarPesagem("Carne", 15.0, 0.250);
			Fachada.cadastrarPesagem("Frango", 10.0, 1.200);
			Fachada.cadastrarPesagem("Salmão", 50.0, 0.300);
			Fachada.cadastrarPesagem("Salada", 8.0, 0.150);
			
			System.out.println("cadastrando bebidas...");
			Fachada.cadastrarBebida("Refrigerante", 5.0, 0.350);
	        Fachada.cadastrarBebida("Suco", 7.0, 0.300);
	        Fachada.cadastrarBebida("Água", 3.0, 0.500);
	        Fachada.cadastrarBebida("Cerveja", 10.0, 0.355);
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
