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
            Integer id = (int) chave;
            TypedQuery<Refeicao> q = manager.createQuery("select r from Refeicao r where r.id = :id", Refeicao.class);
            q.setParameter("x", id);
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
            "select r from Refeicao r join r.consumos c where c.peso > :n", Refeicao.class);
        q.setParameter("n", n);
        return q.getResultList();
    }

    public List<Refeicao> refeicoesMaisNBebidas(int n) {
        TypedQuery<Refeicao> q = manager.createQuery(
            "select r from Refeicao r join r.consumos c where c instance of Bebida group by r having count(c) > :n",
            Refeicao.class);
        q.setParameter("n", n);
        return q.getResultList();
    }
}
