package daojpa;

import java.util.List;

import jakarta.persistence.LockModeType;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Bebida;

public class DAOBebida extends DAO<Bebida> {

    public Bebida read(Object chave) {
        try {
            TypedQuery<Bebida> q;
            if (chave instanceof Integer) {
                Integer id = (Integer) chave;
                q = manager.createQuery("select b from Bebida b where b.id = :x", Bebida.class);
                q.setParameter("x", id);
            } else {
                String nome = (String) chave;
                q = manager.createQuery("select b from Bebida b where b.nome = :x", Bebida.class);
                q.setParameter("x", nome);
            }
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
	public List<Bebida> readAll(){
		manager.clear();
		TypedQuery<Bebida> query = manager.createQuery("select b from Bebida b LEFT JOIN FETCH b.refeicao order by b.id",Bebida.class);
		return  query.getResultList();
	}
}