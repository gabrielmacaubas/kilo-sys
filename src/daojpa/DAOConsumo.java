package daojpa;

import java.util.List;

import jakarta.persistence.LockModeType;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Bebida;
import modelo.Consumo;

public class DAOConsumo extends DAO<Consumo> {

    public Consumo read(Object chave) {
    	try {
    		Integer id = (int) chave;
			TypedQuery<Consumo> q = manager.createQuery("select c from Consumo c where c.id = :x", Consumo.class);
			q.setParameter("x", id);
			Consumo consumo = q.getSingleResult();
			return consumo;
    		
    	}catch(NoResultException e){
			return null;
		}
    }
    
	public List<Consumo> readAll(){
		manager.clear();
		TypedQuery<Consumo> query = manager.createQuery("select c from Consumo b LEFT JOIN FETCH c.refeicao order by c.id",Consumo.class);
		return  query.getResultList();
	}
}