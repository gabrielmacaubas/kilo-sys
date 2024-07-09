package regras_negocio;

import java.util.List;
import java.util.ArrayList;
import daodb4o.DAO;
import daodb4o.DAOBebida;
import daodb4o.DAORefeicao;
import daodb4o.DAOConsumo;
import daodb4o.DAOPesagem;
import modelo.Refeicao;
import modelo.Consumo;
import modelo.Pesagem;
import modelo.Bebida;

public class Fachada {
    private Fachada() {}

    private static DAORefeicao daorefeicao = new DAORefeicao();  
    private static DAOConsumo daoconsumo = new DAOConsumo(); 
    private static DAOPesagem daopesagem = new DAOPesagem(); 
    private static DAOBebida daobebida = new DAOBebida(); 

    public static void inicializar(){
        DAO.open();
    }

    public static void finalizar(){
        DAO.close();
    }

    // Métodos para Refeição
    public static Refeicao cadastrarRefeicao(int id, String data) throws Exception {
        DAO.begin();
        Refeicao refeicao = daorefeicao.read(id);
        if (refeicao != null)
            throw new Exception("Refeição já cadastrada: " + id);
        refeicao = new Refeicao(id, data);
        daorefeicao.create(refeicao);
        DAO.commit();
        return refeicao;
    }

    public static Consumo cadastrarPesagem(int id, String nome, double preco, double peso) throws Exception {
        DAO.begin();
        Consumo consumo = daoconsumo.read(id);
        if (consumo != null)
            throw new Exception("Consumo já cadastrado: " + id);
        consumo = new Pesagem(id, nome, preco, peso);
        daoconsumo.create(consumo);
        DAO.commit();
        return consumo;
    }

    public static Consumo cadastrarBebida(int id, String nome, double preco, double volume) throws Exception {
        DAO.begin();
        Consumo consumo = daoconsumo.read(id);
        if (consumo != null)
            throw new Exception("Consumo já cadastrado: " + id);
        consumo = new Bebida(id, nome, preco, volume);
        daoconsumo.create(consumo);
        DAO.commit();
        return consumo;
    }

    public static void adicionarConsumoNaRefeicao(int idRefeicao, Consumo consumo) throws Exception {
        DAO.begin();
        Refeicao refeicao = daorefeicao.read(idRefeicao);
        if (refeicao == null)
            throw new Exception("Refeição não encontrada: " + idRefeicao);
        refeicao.addConsumo(consumo);
        daorefeicao.update(refeicao);
        DAO.commit();
    }

    public static void alterarRefeicao(int id, String data) throws Exception{
        DAO.begin();
        Refeicao refeicao = daorefeicao.read(id);
        if (refeicao == null)
            throw new Exception("Refeição não encontrada: " + id);
        refeicao.setData(data);
        daorefeicao.update(refeicao);
        DAO.commit();
    }
    
    public static void alterarConsumo(int id, String nome, double preco) throws Exception{
        DAO.begin();
        Consumo consumo = daoconsumo.read(id);
        if (consumo == null)
            throw new Exception("Consumo não encontrado: " + id);
        consumo.setNome(nome);
        consumo.setPreco(preco);
        daoconsumo.update(consumo);
        DAO.commit();
    }

    public static void alterarPesagem(int id, String nome, double preco, double peso) throws Exception{
        DAO.begin();
        Pesagem pesagem = (Pesagem) daopesagem.read(id);
        if (pesagem == null)
            throw new Exception("Pesagem não encontrada: " + id);
        pesagem.setNome(nome);
        pesagem.setPreco(preco);
        pesagem.setPeso(peso);
        daopesagem.update(pesagem);
        DAO.commit();
    }

    public static void alterarBebida(int id, String nome, double preco, double volume) throws Exception{
        DAO.begin();
        Bebida bebida = (Bebida) daobebida.read(id);
        if (bebida == null)
            throw new Exception("Bebida não encontrada: " + id);
        bebida.setNome(nome);
        bebida.setPreco(preco);
        bebida.setVolume(volume);
        daobebida.update(bebida);
        DAO.commit();
    }
    
    public static void excluirRefeicao(int id) throws Exception{
        DAO.begin();
        Refeicao refeicao = daorefeicao.read(id);
        if (refeicao == null)
            throw new Exception("Refeição não encontrada: " + id);
        daorefeicao.delete(refeicao);
        DAO.commit();
    }
    
    public static void excluirConsumo(int id) throws Exception{
        DAO.begin();
        Consumo consumo = daoconsumo.read(id);
        if (consumo == null)
            throw new Exception("Consumo não encontrado: " + id);
        daoconsumo.delete(consumo);
        DAO.commit();
    }

    public static void excluirPesagem(int id) throws Exception{
        DAO.begin();
        Pesagem pesagem = (Pesagem) daopesagem.read(id);
        if (pesagem == null)
            throw new Exception("Pesagem não encontrada: " + id);
        daopesagem.delete(pesagem);
        DAO.commit();
    }

    public static void excluirBebida(int id) throws Exception{
        DAO.begin();
        Bebida bebida = (Bebida) daobebida.read(id);
        if (bebida == null)
            throw new Exception("Bebida não encontrada: " + id);
        daobebida.delete(bebida);
        DAO.commit();
    }
    
    public static List<Refeicao> listarRefeicoes(){
        List<Refeicao> resultados = daorefeicao.readAll();
        return resultados;
    }
    
    public static List<Consumo> listarConsumos(){
        List<Consumo> resultados = daoconsumo.readAll();
        return resultados;
    }

    public static List<Pesagem> listarPesagens(){
        List<Pesagem> resultados = daopesagem.readAll();
        return resultados;
    }

    public static List<Bebida> listarBebidas(){
        List<Bebida> resultados = daobebida.readAll();
        return resultados;
    }

    // Consultas adicionais
    public static List<Refeicao> listarRefeicoesPorData(String data) {
        List<Refeicao> todasRefeicoes = daorefeicao.readAll();
        List<Refeicao> resultado = new ArrayList<>();
        for (Refeicao refeicao : todasRefeicoes) {
            if (refeicao.getData().equals(data)) {
                resultado.add(refeicao);
            }
        }
        return resultado;
    }

    public static List<Refeicao> listarRefeicoesComPesagemMaiorQue(double peso) {
        List<Refeicao> todasRefeicoes = daorefeicao.readAll();
        List<Refeicao> resultado = new ArrayList<>();
        for (Refeicao refeicao : todasRefeicoes) {
            for (Consumo consumo : refeicao.getConsumos()) {
                if (consumo instanceof Pesagem && ((Pesagem) consumo).getPeso() > peso) {
                    resultado.add(refeicao);
                    break;
                }
            }
        }
        return resultado;
    }
    
    public static List<Refeicao> listarRefeicoesComMaisDeNBebidas(int n) {
        List<Refeicao> todasRefeicoes = daorefeicao.readAll();
        List<Refeicao> resultado = new ArrayList<>();
        for (Refeicao refeicao : todasRefeicoes) {
            int contadorBebidas = 0;
            for (Consumo consumo : refeicao.getConsumos()) {
                if (consumo instanceof Bebida) {
                    contadorBebidas++;
                }
            }
            if (contadorBebidas > n) {
                resultado.add(refeicao);
            }
        }
        return resultado;
    }
}
