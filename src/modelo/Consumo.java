package modelo;

public abstract class Consumo {
    private int id;
    private String nome;
    private double preco;

    public Consumo(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Consumo() {}

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