package appconsole;

import modelo.Pesagem;
import jakarta.persistence.EntityManager;
import modelo.Bebida;
import modelo.Refeicao;
import modelo.Consumo;
import regras_negocio.Fachada;

public class Listar {
	protected static EntityManager manager;

	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---listagem de pesagens:");
			for(Pesagem p: Fachada.listarPesagens())
				System.out.println(p);

			System.out.println("\n---listagem de bebidas:");
			for(Bebida b: Fachada.listarBebidas())
				System.out.println(b);
			
			System.out.println("\n---listagem de refeições:");
			for(Refeicao r: Fachada.listarRefeicoes())
				System.out.println(r);

			System.out.println("\n---listagem de consumos:");
			for(Consumo c: Fachada.listarConsumos())
				System.out.println(c);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
