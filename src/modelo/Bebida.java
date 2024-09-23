package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Bebida extends Consumo {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    private double volume;
    
    public Bebida() {}
    public Bebida(String nome, double preco, double volume) {
        super(nome, preco);
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