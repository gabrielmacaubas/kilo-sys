package daojpa;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;
import modelo.Consumo;
import modelo.Refeicao;
import modelo.Pesagem;
import modelo.Bebida;

public class DAORefeicao extends DAO<Refeicao> {

    public Refeicao read(Object chave) {
        try {
            TypedQuery<Refeicao> q;
            if (chave instanceof Integer) {
                Integer id = (Integer) chave;
                q = manager.createQuery("select r from Refeicao r where r.id = :x", Refeicao.class);
                q.setParameter("x", id);
            } else {
                String nome = (String) chave;
                System.out.println(nome);
                q = manager.createQuery("select r from Refeicao r where r.nome = :x", Refeicao.class); // Ajuste conforme o nome real do campo de nome
                q.setParameter("x", nome);
            }
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<Refeicao> refeicoesPorData(String data) {
        TypedQuery<Refeicao> q = manager.createQuery("select r from Refeicao r where r.data = :data", Refeicao.class);
        q.setParameter("data", data);
        return q.getResultList();
    }

    public List<Refeicao> refeicoesAcimaDeNKg(int n) {
        TypedQuery<Refeicao> q = manager.createQuery(
            "select r from Refeicao r join r.consumos c where type(c) = Pesagem and c.peso > :n", Refeicao.class);
        q.setParameter("n", n);
        return q.getResultList();
    }
    
    public List<Refeicao> refeicoesMaisNBebidas(int n) {
        TypedQuery<Refeicao> q = manager.createQuery(
            "select r from Refeicao r join r.consumos c where type(c) = Bebida group by r having count(c) > :n", 
            Refeicao.class);
        q.setParameter("n", n);
        return q.getResultList();
    }

}
