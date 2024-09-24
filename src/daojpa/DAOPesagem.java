package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Pesagem;

public class DAOPesagem extends DAO<Pesagem> {

    public Pesagem read(Object chave) {
        try {
            Integer id = (int) chave;
            TypedQuery<Pesagem> q = manager.createQuery("select p from Pesagem p where p.id = :id", Pesagem.class);
            q.setParameter("x", id);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Pesagem> readAll() {
        TypedQuery<Pesagem> query = manager.createQuery("select p from Pesagem p", Pesagem.class);
        return query.getResultList();
    }

}
