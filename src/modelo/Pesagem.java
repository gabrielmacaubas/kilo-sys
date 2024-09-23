package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pesagem extends Consumo {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private double peso;
    
    public Pesagem() {}
    public Pesagem(String nome, double preco, double peso) {
        super(nome, preco);
        this.peso = peso;
    }

	public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
	public String toString() {
		return "Pesagem [peso=" + peso + ", getId()=" + getId() + ", getNome()=" + getNome() + ", getPreco()="
				+ getPreco() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
}