package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Pesagem;

public class DAOPesagem extends DAO<Pesagem> {

    public Pesagem read(Object chave) {
        try {
            TypedQuery<Pesagem> q;
            if (chave instanceof Integer) {
                Integer id = (Integer) chave;
                q = manager.createQuery("select p from Pesagem p where p.id = :x", Pesagem.class);
                q.setParameter("x", id);
            } else {
                String nome = (String) chave;
                q = manager.createQuery("select p from Pesagem p where p.nome = :x", Pesagem.class); // Ajuste conforme o nome real do campo de nome
                q.setParameter("x", nome);
            }
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
