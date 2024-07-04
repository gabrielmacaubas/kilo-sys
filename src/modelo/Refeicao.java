package modelo;

import java.util.List;

public class Refeicao {
    private int id;
    private String data;
    private List<Consumo> listaConsumo;
    private double valorPago;

    public Refeicao() {}

    public Refeicao(String data, List<Consumo> listaConsumo) {
    	super();
        this.data = data;
        this.listaConsumo = listaConsumo;
        
        this.valorPago = 0;
        for (Consumo consumo : listaConsumo) {
            this.valorPago += consumo.getPreco();
        }
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Consumo> getListaConsumo() {
        return listaConsumo;
    }

    public double getValorPago() {
        return valorPago;
    }
    
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

    @Override
    public String toString() {
        StringBuilder consumos = new StringBuilder();
        for (Consumo consumo : listaConsumo) {
            consumos.append(consumo.toString()).append(", ");
        }
        return "id=" + this.id + ", data=" + this.data + ", valorPago=" + this.valorPago + ", consumos=[" + consumos.toString() + "]";
    }
}