package daojpa;

import java.util.List;

import jakarta.persistence.LockModeType;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Bebida;

public class DAOBebida extends DAO<Bebida> {

    public Bebida read(Object chave) {
    	try {
    		Integer id = (int) chave;
			TypedQuery<Bebida> q = manager.createQuery("select b from Bebida b where b.id = :x", Bebida.class);
			q.setParameter("x", id);
			Bebida bebida = q.getSingleResult();
			return bebida;
    		
    	}catch(NoResultException e){
			return null;
		}
    }
    
	public List<Bebida> readAll(){
		manager.clear();
		TypedQuery<Bebida> query = manager.createQuery("select b from Bebida b LEFT JOIN FETCH b.refeicao order by b.id",Bebida.class);
		return  query.getResultList();
	}
}