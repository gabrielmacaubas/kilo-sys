package regras_negocio;

import java.util.List;
import java.util.ArrayList;
import daojpa.DAO;
import daojpa.DAOBebida;
import daojpa.DAORefeicao;
import daojpa.DAOConsumo;
import daojpa.DAOPesagem;
import modelo.Refeicao;
import modelo.Consumo;
import modelo.Pesagem;
import modelo.Bebida;

public class Fachada {
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

    public static Refeicao cadastrarRefeicao(String data) throws Exception {
        DAO.begin();
        Refeicao refeicao = daorefeicao.read(data);
        refeicao = new Refeicao(data);
        daorefeicao.create(refeicao);
        DAO.commit();
        return refeicao;
    }

    public static Consumo cadastrarPesagem(String nome, double preco, double peso) throws Exception {
        DAO.begin();
        Consumo consumo = daoconsumo.read(nome);
        if (consumo != null) {
            throw new Exception("Consumo já cadastrado: " + nome);
        }
        consumo = new Pesagem(nome, preco, peso);
        daoconsumo.create(consumo);
        DAO.commit();
        return consumo;
    }

    public static Consumo cadastrarBebida(String nome, double preco, double volume) throws Exception {
        DAO.begin();
        Consumo consumo = daoconsumo.read(nome);
        if (consumo != null) {
			DAO.rollback();
			throw new Exception("bebida ja existe");
        }
        consumo = new Bebida(nome, preco, volume);
        daoconsumo.create(consumo);
        DAO.commit();
        return consumo;
    }

    public static void adicionarConsumoNaRefeicao(int idRefeicao, int idConsumo) throws Exception {
        DAO.begin();
        
        Refeicao refeicao = daorefeicao.read(idRefeicao);
        if (refeicao == null) {
        	DAO.rollback();
            throw new Exception("Refeição não encontrada: " + idRefeicao);
        }
        
        Consumo consumo = daoconsumo.read(idConsumo);
        if (consumo == null) {
        	DAO.rollback();
            throw new Exception("Consumo não encontrada: " + idConsumo);
        }
        
        refeicao.addConsumo(consumo);
        daorefeicao.update(refeicao);
        DAO.commit();
    }

    public static void alterarRefeicao(int id, String data) throws Exception{
        DAO.begin();
        Refeicao refeicao = daorefeicao.read(id);
        if (refeicao == null) {
        	DAO.rollback();
        	throw new Exception("Refeição não encontrada: " + id);
        }
            
        refeicao.setData(data);
        daorefeicao.update(refeicao);
        DAO.commit();
    }
    
    public static void alterarConsumo(int id, String nome, double preco) throws Exception{
        DAO.begin();
        Consumo consumo = daoconsumo.read(id);
        if (consumo == null) {
        	DAO.rollback();
        	throw new Exception("Consumo não encontrado: " + id);
        }

        consumo.setNome(nome);
        consumo.setPreco(preco);
        daoconsumo.update(consumo);
        DAO.commit();
    }

    public static void alterarPesagem(int id, String nome, double preco, double peso) throws Exception{
        DAO.begin();
        Pesagem pesagem = (Pesagem) daopesagem.read(id);
        if (pesagem == null) {
        	DAO.rollback();
        	throw new Exception("Pesagem não encontrada: " + id);
        }
            
        pesagem.setNome(nome);
        pesagem.setPreco(preco);
        pesagem.setPeso(peso);
        daopesagem.update(pesagem);
        DAO.commit();
    }

    public static void alterarBebida(int id, String nome, double preco, double volume) throws Exception{
        DAO.begin();
        Bebida bebida = (Bebida) daobebida.read(id);
        if (bebida == null) {
        	DAO.rollback();
        	throw new Exception("Bebida não encontrada: " + id);
        }
            
        bebida.setNome(nome);
        bebida.setPreco(preco);
        bebida.setVolume(volume);
        daobebida.update(bebida);
        DAO.commit();
    }
    
    public static void excluirRefeicao(int id) throws Exception{
        DAO.begin();
        Refeicao refeicao = daorefeicao.read(id);
        if (refeicao == null) {
        	DAO.rollback();
        	throw new Exception("Refeição não encontrada: " + id);
        }
            
        daorefeicao.delete(refeicao);
        DAO.commit();
    }
    
    public static void excluirConsumo(int id, String nome) throws Exception{
        DAO.begin();
        Consumo consumo = daoconsumo.read(id);
        if (consumo == null) {
        	DAO.rollback();
        	throw new Exception("Consumo não encontrado: " + id);
        }
            
        daoconsumo.delete(consumo);
        DAO.commit();
    }

    public static void excluirPesagem(int id) throws Exception{
        DAO.begin();
        Pesagem pesagem = (Pesagem) daopesagem.read(id);
        if (pesagem == null) {
        	DAO.rollback();
        	throw new Exception("Pesagem não encontrada: " + id);
        }
            
        daopesagem.delete(pesagem);
        DAO.commit();
    }

    public static void excluirBebida(int id) throws Exception{
        DAO.begin();
        Bebida bebida = (Bebida) daobebida.read(id);
        if (bebida == null) {
        	DAO.rollback();
        	throw new Exception("Bebida não encontrada: " + id);
        }
            
        daobebida.delete(bebida);
        DAO.commit();
    }
    
    // usar begin e commit nas leituras para tratar concorrencia (transaction)
    
    public static List<Refeicao> listarRefeicoes(){
    	DAO.begin();
        List<Refeicao> resultados = daorefeicao.readAll();
        DAO.commit();
        return resultados;
    }
    
    public static List<Consumo> listarConsumos(){
    	DAO.begin();
        List<Consumo> resultados = daoconsumo.readAll();
        DAO.commit();
        return resultados;
    }

    public static List<Pesagem> listarPesagens(){
    	DAO.begin();
        List<Pesagem> resultados = daopesagem.readAll();
        DAO.commit();
        return resultados;
    }

    public static List<Bebida> listarBebidas(){
    	DAO.begin();
        List<Bebida> resultados = daobebida.readAll();
        DAO.commit();
        return resultados;
    }

    // Consultas adicionais
    public static List<Refeicao> listarRefeicoesPorData(String data) {
    	DAO.begin();
        List<Refeicao> todasRefeicoes = daorefeicao.refeicoesPorData(data);
        DAO.commit();

        return todasRefeicoes;
    }

    public static List<Refeicao> listarRefeicoesComPesagemMaiorQue(double peso) {
    	DAO.begin();
        List<Refeicao> todasRefeicoes = daorefeicao.refeicoesAcimaDeNKg((int)peso);
        DAO.commit();
        
        return todasRefeicoes;
    }
    
    public static List<Refeicao> listarRefeicoesComMaisDeNBebidas(int n) {
    	DAO.begin();
        List<Refeicao> todasRefeicoes = daorefeicao.refeicoesMaisNBebidas(1);
        DAO.commit();

        return todasRefeicoes;
    }
}
