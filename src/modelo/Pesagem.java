package modelo;

public class Pesagem extends Consumo {
    private double peso;

    public Pesagem(int id , String nome, double preco, double peso) {
        super(id ,nome, preco);
        
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