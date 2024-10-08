package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity 
public abstract class Consumo {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne
	private Refeicao refeicao;
	
    private String nome;
    private double preco;
    
    public Consumo() {}
    public Consumo(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "id=" + id + ", nome=" + nome + ", preco=" + preco;
    }
}