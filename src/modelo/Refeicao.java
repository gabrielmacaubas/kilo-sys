package modelo;

import java.util.ArrayList;
import java.util.List;

public class Refeicao {
    private int id;
    private String data;
    private List<Consumo> consumos;
    private double valorPago;

    public Refeicao(int id, String data) {
        this.id = id;
        this.data = data;
        this.consumos = new ArrayList<>();
        this.valorPago = 0.0;
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

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void addConsumo(Consumo consumo) {
        this.consumos.add(consumo);
        calcularValorPago();
    }

    public void removeConsumo(Consumo consumo) {
    	this.consumos.remove(consumo);
        calcularValorPago();
    }

    private void calcularValorPago() {
        valorPago = 0.0;
        for (Consumo consumo : consumos) {
            valorPago += consumo.getPreco();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder consumos = new StringBuilder();
        for (Consumo consumo : this.consumos) {
            consumos.append(consumo.toString()).append(", ");
        }
        return "id=" + this.id + ", data=" + this.data + ", valorPago=" + this.valorPago + ", consumos=[" + consumos.toString() + "]";
    }
}
