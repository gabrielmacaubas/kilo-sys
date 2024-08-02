package daodb4o;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Query;

import modelo.Consumo;


public class DAOConsumo extends DAO<Consumo> {

    public Consumo read(Object chave) {
    	int id = (int) chave;
        Query q = manager.query();
        q.constrain(Consumo.class);
        q.descend("id").constrain(id);
        List<Consumo> resultados = q.execute();
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }
    
	public void create(Consumo obj){
		int novoid = super.gerarId();
		obj.setId(novoid);
		manager.store( obj );
	}
	
	public List<Consumo> buscarTodos(){
        Query q = manager.query();
        q.constrain(Consumo.class);
        return q.execute();
    }
	
	public List<Consumo> consumosPorNome(String nome) {
	    Query q = manager.query();
	    q.constrain(Consumo.class);
	    q.descend("nome").constrain(nome);
	    return q.execute();
	}

	public List<Consumo> consumosPorTipo(Class<?> tipo) {
	    Query q = manager.query();
	    q.constrain(Consumo.class);
	    q.constrain(new Object() {
	        public void evaluate(Candidate candidate) {
	            Consumo consumo = (Consumo) candidate.getObject();
	            if (tipo.isInstance(consumo)) {
	                candidate.include(true);
	            } else {
	                candidate.include(false);
	            }
	        }
	    });
	    return q.execute();
	}

	
}


