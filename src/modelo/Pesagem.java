package modelo;

public class Pesagem extends Consumo {
    private double peso;

    public Pesagem(int id , String nome, double preco, double peso) {
        super(id ,nome, preco);
        this.peso = peso;
    }

    public Pesagem() {}


	public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return super.toString() + ", peso=" + peso;
    }
}