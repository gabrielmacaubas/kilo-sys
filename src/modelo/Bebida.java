package modelo;

public class Bebida extends Consumo {
    private double volume;

    public Bebida(int  id ,String nome, double preco, double volume) {
        super(id, nome, preco);
        this.volume = volume;
    }


    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return super.toString() + ", volume=" + volume;
    }
}